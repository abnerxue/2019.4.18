package ck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import java.sql.ResultSet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import ck.po.*;

/**
 * @Author: alex
 * @Description:
 * @Date: 2019/2/14 15:34
 * @Modified:
 **/
public class ServerObj
{
	private static int port = 7777;
	private static String URL = "jdbc:sqlserver://192.168.0.7:1433;DatabaseName=qyzhsfpt";
	private static String USER = "sa";
	private static String PASSWORD = "Ckzx123";

	public static long prescriptionIncrementIn3Seconds; // 3秒内处方增量
	public static long notPassPrescriptionIncrementIn3Seconds; // 3秒内未通过处方增量

	private static final long PERIOD_TIME = 24 * 60 * 60 * 1000; // redis 首页数据清空周期

	public static Connection getConnection()
	{
		Connection connection = null;
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try
			{
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return connection;
	}

	public static void init()
	{
		// get redis connection and flush cashes
		org.redisson.config.Config redissonConfig = new org.redisson.config.Config();
//		redissonConfig.useSingleServer().setAddress("redis://192.168.0.144:6379");
		redissonConfig.useSingleServer().setAddress("redis://192.168.0.7:6379");
		redissonConfig.useSingleServer().setAddress("redis://localhost:6379");
		RedissonClient redisson = Redisson.create(redissonConfig);

		Map<Object, Object> prescriptionIncrement = redisson.getMap("prescriptionIncrement"); // 存放增量，用于首页分时增量图
		prescriptionIncrement.put("all", new HashMap<Object, Object>()); // 所有处方分时增量
		prescriptionIncrement.put("notPassed", new HashMap<Object, Object>()); // 不通过处方分时增量

		// read rules from DB and push to cache
		Connection connection = ServerObj.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try
		{
			// read t_audit_rules to redis
			st = connection.createStatement();
			rs = st.executeQuery("SELECT id, code, name, action, [enable] FROM t_audit_rules");
			RList<Object> auditRules = redisson.getList("auditRules"); // 复审规则
			auditRules.clear();
			while (rs.next())
			{
				AuditRule auditRulesObj = new AuditRule();
				auditRulesObj.setId(rs.getInt(1));
				auditRulesObj.setCode(rs.getString(2));
				auditRulesObj.setName(rs.getString(3));
				auditRulesObj.setAction(rs.getInt(4));
				auditRulesObj.setEnable(rs.getBoolean(5));
				auditRules.add(auditRulesObj);
			}

			// read t_config to redis
			rs = st.executeQuery("SELECT id, name, [values] FROM t_config");
			RMap<Object, Object> configs = redisson.getMap("configs"); // 系统配置
			configs.clear();
			while (rs.next())
			{
				Config configObj = new Config();
				configObj.setId(rs.getInt(1));
				configObj.setName(rs.getString(2));
				configObj.setValues(rs.getString(3));
				configs.put(rs.getString(2), configObj);
			}

			// read data from t_medcine to redis
			rs = st.executeQuery("SELECT id, name, type, category, subcategory, dosage_form, standard_source, dose_recommend_per_time, dose_recommend_per_day, " + "dose_max_per_time, dose_max_per_day, frequency, total_dose, max_duration, max_multidays_dose, route_of_medication, skin_test, " + "mental_stupefacient_Toxic, need_audit, specification, daily_dose, platform_standard, is_antibiotic FROM t_medcine");
			// read t_medcine to redis
			rs = st.executeQuery("SELECT id, name, type, category, subcategory, dosage_form, standard_source, dose_recommend_per_time, dose_recommend_per_day, \r\n" + 
					"dose_max_per_time, dose_max_per_day, frequency, total_dose, max_duration, max_multidays_dose, route_of_medication, skin_test, \r\n" + 
					"mental_stupefacient_Toxic, need_audit, specification, daily_dose, platform_standard, is_antibiotic\r\n" + 
					"FROM t_medcine");
			RList<Object> medcines = redisson.getList("medcines"); // 药品信息
			medcines.clear();
			while (rs.next())
			{
				Medcine medcineObj = new Medcine();
				medcineObj.setId(rs.getInt(1));
				medcineObj.setName(rs.getString(2));
				medcineObj.setType(rs.getInt(3));
				medcineObj.setCategory(rs.getInt(4));
				medcineObj.setSubcategory(rs.getInt(5));
				medcineObj.setDosageForm(rs.getInt(6));
				medcineObj.setStandardSource(rs.getInt(7));
				medcineObj.setDoseRecommendPerTime(rs.getInt(8));
				medcineObj.setDoseRecommendPerDay(rs.getInt(9));
				medcineObj.setDoseMaxPerTime(rs.getInt(10));
				medcineObj.setDoseMaxPerDay(rs.getInt(11));
				medcineObj.setFrequency(rs.getInt(12));
				medcineObj.setTotalDose(rs.getInt(13));
				medcineObj.setMaxDuration(rs.getInt(14));
				medcineObj.setMaxMultidaysDose(rs.getInt(15));
				medcineObj.setRouteOfMedication(rs.getString(16));
				medcineObj.setSkinTest(rs.getBoolean(17));
				medcineObj.setMentalStupefacientToxic(rs.getString(18));
				medcineObj.setNeedAudit(rs.getBoolean(19));
				medcineObj.setSpecification(rs.getString(20));
				medcineObj.setDailyDose(rs.getInt(21));
				medcineObj.setPlatformStandard(rs.getBoolean(22));
				medcineObj.setAntibiotic(rs.getBoolean(23));
				medcines.add(medcineObj);
			}

			// read t_dept_restriction to redis
			rs = st.executeQuery("SELECT id, medcine_id, dept_id, action FROM t_dept_restriction");
			RList<Object> deptRestrictions = redisson.getList("deptRestrictions"); // 科室药品限制关系
			deptRestrictions.clear();
			while (rs.next())
			{
				DeptRestriction deptRestrictionObj = new DeptRestriction();
				deptRestrictionObj.setId(rs.getInt(1));
				deptRestrictionObj.setMedcineId(rs.getInt(2));
				deptRestrictionObj.setDeptId(rs.getInt(3));
				deptRestrictionObj.setAction(rs.getInt(4));
				deptRestrictions.add(deptRestrictionObj);
			}

			// read t_audit_center to redis
			rs = st.executeQuery("SELECT id, audit_center_name, ip, platform_audit_state, unit_name_id FROM t_audit_center");
			RList<Object> auditCenters = redisson.getList("auditCenters"); // 审方中心
			auditCenters.clear();
			while (rs.next())
			{
				AuditCenter auditCenterObj = new AuditCenter();
				auditCenterObj.setId(rs.getInt(1));
				auditCenterObj.setAuditCenterName(rs.getString(2));
				auditCenterObj.setIp(rs.getString(3));
				auditCenterObj.setPlatformAuditState(rs.getBoolean(4));
				auditCenterObj.setUnitNameId(rs.getInt(5));
				auditCenters.add(auditCenterObj);
			}

			// read t_audit_state to redis
			rs = st.executeQuery("SELECT id, desp FROM t_audit_state");
			RList<Object> auditStates = redisson.getList("auditStates"); // 审核状态
			auditStates.clear();
			while (rs.next())
			{
				AuditState auditStateObj = new AuditState();
				auditStateObj.setId(rs.getInt(1));
				auditStateObj.setDesp(rs.getString(2));
				auditStates.add(auditStateObj);
			}

			// read t_unit_name to redis
			rs = st.executeQuery("SELECT id, unit_name, district_id FROM t_unit_name");
			RList<Object> unitNames = redisson.getList("unitNames"); // 医院
			unitNames.clear();
			while (rs.next())
			{
				UnitName unitNameObj = new UnitName();
				unitNameObj.setId(rs.getInt(1));
				unitNameObj.setUnitName(rs.getString(2));
				unitNameObj.setDistrictId(rs.getInt(3));
				unitNames.add(unitNameObj);
			}

			// read t_district to redis
			rs = st.executeQuery("SELECT id, district FROM t_district");
			RList<Object> districts = redisson.getList("districts"); // 区域
			districts.clear();
			while (rs.next())
			{
				District districtObj = new District();
				districtObj.setDistrictId(rs.getInt(1));
				districtObj.setDistrictName(rs.getString(2));
				districts.add(districtObj);
			}

			// read t_medical_dept to redis
			rs = st.executeQuery("SELECT id, name, hospital_id FROM t_medical_dept");
			RList<Object> medicalDepts = redisson.getList("medicalDepts"); // 医院科室
			medicalDepts.clear();
			while (rs.next())
			{
				MedicalDept medicalDeptObj = new MedicalDept();
				medicalDeptObj.setId(rs.getInt(1));
				medicalDeptObj.setName(rs.getString(2));
				medicalDeptObj.setHospitalId(rs.getInt(3));
				medicalDepts.add(medicalDeptObj);
			}

			// read t_hospital_staff
			rs = st.executeQuery("SELECT id, name, type, medical_dept_id FROM t_hospital_staff");
			RList<Object> hospitalStaffs = redisson.getList("hospitalStaffs"); // 医院人员
			hospitalStaffs.clear();
			while (rs.next())
			{
				HospitalStaff hospitalStaffObj = new HospitalStaff();
				hospitalStaffObj.setId(rs.getInt(1));
				hospitalStaffObj.setName(rs.getString(2));
				hospitalStaffObj.setType(rs.getInt(3));
				hospitalStaffObj.setMedicalDeptId(rs.getInt(4));
				hospitalStaffs.add(hospitalStaffObj);
			}

			// read t_diagnosis
			rs = st.executeQuery("SELECT id, desp, icd10 FROM t_diagnosis");
			RList<Diagnosis> diagnoses = redisson.getList("diagnoses"); // 诊断
			diagnoses.clear();
			while (rs.next())
			{
				Diagnosis diagnosisObj = new Diagnosis();
				diagnosisObj.setId(rs.getInt(1));
				diagnosisObj.setDiagnosisName(rs.getString(2));
				diagnosisObj.setIcd10(rs.getString(3));
				diagnoses.add(diagnosisObj);
			}

			RList<Object> dosageLimits = redisson.getList("dosageLimits"); // 用量限制
			dosageLimits.clear();
			rs = st.executeQuery("SELECT id, rule_name, dosage_pre, doesage_post, unit_name_pre, unit_name_post, relational_operator_pre, relational_operator_post, [level], medcine_id FROM t_dosage_limit");
			while (rs.next())
			{
				DosageLimit dosageLimitObj = new DosageLimit();
				dosageLimitObj.setId(rs.getInt(1));
				dosageLimitObj.setRuleName(rs.getString(2));
				dosageLimitObj.setDosagePre(rs.getDouble(3));
				dosageLimitObj.setDoesagePost(rs.getDouble(4));
				dosageLimitObj.setUnitNamePre(rs.getInt(5));
				dosageLimitObj.setUnitNamePost(rs.getInt(6));
				dosageLimitObj.setRelationalOperatorPre(rs.getInt(7));
				dosageLimitObj.setRelationalOperatorPost(rs.getInt(8));
				dosageLimitObj.setLevel(rs.getInt(9));
				dosageLimitObj.setMedcineId(rs.getInt(10));
				dosageLimits.add(dosageLimitObj);
			}

			RList<UnitDictionary> unitDictionarys = redisson.getList("unitDictionarys"); // 单位字典表
			unitDictionarys.clear(); // 清空
			// 将数据库单位字典表数据读取到redis中
			rs = st.executeQuery("SELECT id, unit FROM t_unit_dictionary");
			while (rs.next())
			{
				UnitDictionary unitDictionaryObj = new UnitDictionary();
				unitDictionaryObj.setId(rs.getInt(1));
				unitDictionaryObj.setUnitName(rs.getString(2));
				unitDictionarys.add(unitDictionaryObj);
			}

			RList<OperatorDictionary> operatorDictionarys = redisson.getList("operatorDictionarys");// 运算符字典表
			operatorDictionarys.clear();
			rs = st.executeQuery("SELECT id, operator FROM t_operator_dictionary");
			while (rs.next())
			{
				OperatorDictionary operatorDictionaryObj = new OperatorDictionary();
				operatorDictionaryObj.setId(rs.getInt(1));
				operatorDictionaryObj.setOperator(rs.getString(2));
				operatorDictionarys.add(operatorDictionaryObj);
			}

			RList<AuditLevel> auditLevels = redisson.getList("auditLevels");// 审核级别
			auditLevels.clear();
			rs = st.executeQuery("SELECT id, desp FROM t_audit_level");
			while (rs.next())
			{
				AuditLevel auditLevelObj = new AuditLevel();
				auditLevelObj.setId(rs.getInt(1));
				auditLevelObj.setDesp(rs.getString(2));
				auditLevels.add(auditLevelObj);
			}

			RList<DurationLimit> durationLimits = redisson.getList("durationLimits");
			durationLimits.clear();
			rs = st.executeQuery("SELECT id, medcine_id, rule_name, lowerbound, upperbound, [level] FROM t_duration_limit");
			while (rs.next())
			{
				DurationLimit durationLimitObj = new DurationLimit();
				durationLimitObj.setId(rs.getInt(1));
				durationLimitObj.setMedcineId(rs.getInt(2));
				durationLimitObj.setRuleName(rs.getString(3));
				durationLimitObj.setLowerBound(rs.getDouble(4));
				durationLimitObj.setUpperBound(rs.getDouble(5));
				durationLimitObj.setLevel(rs.getInt(6));
				durationLimits.add(durationLimitObj);
			}

			RList<DosageForm> dosageForms = redisson.getList("dosageForms");
			dosageForms.clear();
			rs = st.executeQuery("SELECT id, dosage_form_name FROM t_dosage_form_dictionary");
			while (rs.next())
			{
				DosageForm dosageForm = new DosageForm();
				dosageForm.setId(rs.getInt(1));
				dosageForm.setDosage_form_name(rs.getString(2));
				dosageForms.add(dosageForm);
			}

			// read data from t_check_items to redis
			RList<CheckItems> checkItems = redisson.getList("checkItems");
			checkItems.clear();
			rs = st.executeQuery("SELECT id, name, unit_name FROM t_check_items");
			while (rs.next())
			{
				CheckItems checkItem = new CheckItems();
				checkItem.setId(rs.getInt(1));
				checkItem.setName(rs.getString(2));
				checkItem.setUnitName(rs.getString(3));
				checkItems.add(checkItem);
			}

			// read data from t_check_limit to redis
			RList<CheckLimit> checkLimits = redisson.getList("checkLimits");
			checkLimits.clear();
			rs = st.executeQuery("SELECT id, medicine_id, rule_name, lowerbound, upperbound, check_items_id, [level], type FROM t_check_limit");
			while (rs.next())
			{
				CheckLimit checkLimit = new CheckLimit();
				checkLimit.setId(rs.getInt(1));
				checkLimit.setMedcineId(rs.getInt(2));
				checkLimit.setRuleName(rs.getString(3));
				checkLimit.setLowerBound(rs.getFloat(4));
				checkLimit.setUpperBound(rs.getFloat(5));
				checkLimit.setCheck_items_id(rs.getInt(6));
				checkLimit.setLevel(rs.getString(7));
				checkLimit.setType(rs.getInt(8));
				checkLimits.add(checkLimit);
			}

			if (!rs.isClosed())
			{
				rs.close();
			}
			if (!st.isClosed())
			{
				st.close();
			}
			if (!connection.isClosed())
			{
				connection.close();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		if (!redisson.isShutdown())
		{
			if (!redisson.isShuttingDown())
			{
				redisson.shutdown();
			}
		}

		// start PrescriptionStoreThread，将redis中的处方数据存入数据库
		PrescriptionStoreThread _thread = new PrescriptionStoreThread();
		_thread.start();

		// start PrescriptionIncrementThread，记录分时增量并存入redis
		new Thread(new PrescriptionIncrementThread()).start();

	}

	public static void start()
	{
		ServerBootstrap bootstrap = new ServerBootstrap();
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		try
		{
			bootstrap.group(boss, worker);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.option(ChannelOption.SO_BACKLOG, 2048);
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
			bootstrap.childHandler(new ChannelInitializer<Channel>()
			{
				@Override
				protected void initChannel(Channel ch) throws Exception
				{
					ch.pipeline().addLast(new ObjectEncoder());
					ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
					ch.pipeline().addLast(new ObjectServerHandler());
				}
			});
			ChannelFuture channelFuture = bootstrap.bind(port);
			init();
			System.out.println("server start");
			channelFuture.channel().closeFuture().sync();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}

	public static void timerForResetPrescriptionsInDifferentPeriods()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date firstTime = calendar.getTime();
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				RedissonClient redissonClient = Redisson.create();
				redissonClient.getList("PrescriptionsInDifferentPeriods").clear();
			}
		}, firstTime, PERIOD_TIME);

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		start();
		timerForResetPrescriptionsInDifferentPeriods();

	}
}
