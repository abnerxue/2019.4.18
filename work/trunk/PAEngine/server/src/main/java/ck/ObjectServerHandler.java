package ck;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.colorchooser.ColorChooserComponentFactory;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

import ck.dto.DiagnosisDTO;
import ck.dto.MedcineDTO;
import ck.po.AuditCenter;
import ck.po.AuditRule;
import ck.po.Config;
import ck.po.DeptRestriction;
import ck.po.DosageLimit;
import ck.po.DurationLimit;
import ck.po.Medcine;
import ck.po.UnitName;
import ck.dto.PrescriptionDTO;
import ck.vo.PrescriptionVO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ObjectServerHandler extends ChannelInboundHandlerAdapter
{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{

		ServerObj.prescriptionIncrementIn3Seconds++; // 每接收到一条处方，处方总数加一

		PrescriptionDTO prescriptionDTO = (PrescriptionDTO) msg; // PrescriptionDTO 处方对象
		String auditCenterName = prescriptionDTO.getAuditCenter(); // 审方中心名称

		String hospitalName = prescriptionDTO.getMedicalUnit(); // 医院名称
		UnitName unitName = null; // 医院对象

		// 创建redissonClient 用于读取redis中存放的各种信息
		org.redisson.config.Config redissonConfig = new org.redisson.config.Config();
//		redissonConfig.useSingleServer().setAddress("redis://192.168.0.144:6379");
		redissonConfig.useSingleServer().setAddress("redis://192.168.0.7:6379");
		redissonConfig.useSingleServer().setAddress("redis://localhost:6379");
		RedissonClient redissonClient = Redisson.create(redissonConfig);
		// 创建redissonClient 用于读取redis中存放的各种信息

		List<PrescriptionVO> prescriptionVOs = redissonClient.getList("prescriptionVOs"); // 获取redis中所有处方
		RList<UnitName> unitNames = redissonClient.getList("unitNames"); // 获取所有医院信息
		Map<String, Config> configs = redissonClient.getMap("configs"); // 获取所有系统参数
		List<AuditRule> auditRules = redissonClient.getList("auditRules"); // 获取所有审核规则

		List<Medcine> medcines = redissonClient.getList("medcines"); // 获取所有药品信息

		List<DeptRestriction> deptRestrictions = redissonClient.getList("deptRestrictions"); // 获取所有医院科室药品限制关系信息
		Map<String, Integer> auditResults = new HashMap<String, Integer>(); // 用于存放复审完成后产生的复审结果

		List<MedcineDTO> medcineDTOs = prescriptionDTO.getMedcineItems(); // 处方药品信息
		List<Medcine> medcineDTOsToMedcines = new ArrayList<Medcine>(); // 处方药品信息对应的平台药品信息

		// 处方单中的审方中心名称是否为空
		AuditCenter auditCenter = null; // 审方中心
		if (auditCenterName == null || auditCenterName.isEmpty())
		{
			return;
		}

		RList<AuditCenter> auditCentersList = redissonClient.getList("auditCentersList");
		for (AuditCenter auditCenterObj : auditCentersList)
		{
			if (auditCenterName == auditCenterObj.getAuditCenterName())
			{
				auditCenter = auditCenterObj;
				break;
			}
		}

		// 不存在，return
		if (auditCenter == null)
		{
			return;
		}

		// 判断处方中的医院名称是否存在
		if (hospitalName == null || hospitalName.isEmpty())
		{
			return;
		}

		// 处方单中的医院名称是否在平台中存在
		for (UnitName unitNameObj : unitNames)
		{
			if (hospitalName == unitNameObj.getUnitName())
			{
				unitName = unitNameObj;
				break;
			}
		}

		// 不存在return
		if (unitName == null)
		{
			return;
		}

		// 获取处方药品信息对应的平台药品信息
		for (MedcineDTO medcineDTO : medcineDTOs)
		{
			for (Medcine medcine : medcines)
			{
				if (medcineDTO.getCommonName().equals(medcine.getName()) && auditCenter.getId() == medcine.getStandardSource() && medcineDTO.getSpecification().equals(medcine.getSpecification()))
				{
					medcineDTOsToMedcines.add(medcine);
					break;
				}
			}
		}

		List<MedcineDTO> historyMedcineDTOsTotal = new ArrayList<MedcineDTO>(); // 用于存放15天内相同社保号或身份证号的所有处方包含药品总和

		PrescriptionVO prescriptionVO = new PrescriptionVO(); // prescriptionVO,用于存放PrescriptionDTO对象的所有信息以及复审结果，然后会存到redis中

		// int historicalPrescriptionAuditScope = 0; //历史处方审核范围天数
		// if (configMap.containsKey("历史处方审核范围"))
		// {
		// if (configMap.get("历史处方审核范围").getValues() != null &&
		// !configMap.get("历史处方审核范围").getValues().isEmpty())
		// {
		// historicalPrescriptionAuditScope =
		// Integer.parseInt(configMap.get("历史处方审核范围").getValues());
		// }
		// }

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date endingTime = prescriptionDTO.getSubmmitTime();
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(endingTime);
		// calendar.add(Calendar.DAY_OF_MONTH, -historicalPrescriptionAuditScope);
		// Date startingTime = calendar.getTime();
		// System.out.println(sdf.format(startingTime));
		// System.out.println(sdf.format(endingTime));

		List<PrescriptionVO> historyPrescriptionVOs = new ArrayList<PrescriptionVO>(); // 历史处方

		// 获取病人Z天内的所有处方集合
		for (PrescriptionVO pre : prescriptionVOs)
		{
			// 社保号码
			if (prescriptionDTO.getPatientInfo().getSocialSecurityNumber() != null && !prescriptionDTO.getPatientInfo().getSocialSecurityNumber().isEmpty())
			{
				if (prescriptionDTO.getPatientInfo().getSocialSecurityNumber().equals(pre.getPatientInfo().getSocialSecurityNumber()))
				{
					historyPrescriptionVOs.add(pre);
				}
			}

			// 身份证号码
			if (prescriptionDTO.getPatientInfo().getIdNumber() != null && !prescriptionDTO.getPatientInfo().getIdNumber().isEmpty())
			{
				if (prescriptionDTO.getPatientInfo().getIdNumber().equals(pre.getPatientInfo().getIdNumber()))
				{
					historyPrescriptionVOs.add(pre);
				}
			}

		}

		// 获取历史处方药物总和
		for (PrescriptionVO prescriptionVO2 : historyPrescriptionVOs)
		{
			if (prescriptionVO2.getMedcineItems() != null)
			{
				historyMedcineDTOsTotal.addAll(prescriptionVO2.getMedcineItems());
			}
		}

		List<Medcine> medcineDTOsToMedcinesSum = new ArrayList<Medcine>(); // 历史处方药物总和对应的平台机构药品信息总和

		// 获取历史处方药物总和对应的平台机构药品信息总和
		for (MedcineDTO medcineDTO : historyMedcineDTOsTotal)
		{
			for (Medcine medcine : medcines)
			{
				if (medcineDTO.getCommonName().equals(medcine.getName()))
				{
					if (auditCenter.getId() == medcine.getStandardSource())
					{
						if (medcineDTO.getSpecification().equals(medcine.getSpecification()))
						{
							medcineDTOsToMedcinesSum.add(medcine);
							break;
						}
					}
				}
			}
		}

		// 遍历复审规则
		for (AuditRule auditRuleObj : auditRules)
		{
			// 判断重复用药
			if ("重复用药".equals(auditRuleObj.getName().trim()))
			{
				// 重复用药启用
				if (auditRuleObj.getAction() == 1)
				{
					/*
					 * 1、判断本次处方中的每一个药品与历史处方中的药品是否存在两个药物是属于同一大类
					 * 2、判断本次处方中的每一个药品与历史处方中的药品是否存在两个药物是属于同一个标签 3、1和2满足一项则为重复用药
					 */
					for (MedcineDTO medcineDTO : medcineDTOs) // 遍历本次处方中的所有药品
					{
						for (PrescriptionVO historyPrescriptionVO : historyPrescriptionVOs)
						{
							for (MedcineDTO historyMedcineDTO : historyPrescriptionVO.getMedcineItems())
							{
								
							}
						}
					}
					}
				}

			// 0002 疗程总剂量异常：在Z天内，所有处方所开药物超出限制用量x。若历史处方中无相同药物则不判断
			if ("疗程总剂量异常".equals(auditRuleObj.getName().trim()))
			{
				if (auditRuleObj.getAction() == 1)
				{
					int _sameMedcineCounter; // 历史处方是否包含相同药物
					double _totalDoseSum; // Z天内，所有处方同一药品总剂量之和
					int _medcineId = 0;
					RList<DosageLimit> dosageLimits = redissonClient.getList("dosageLimits");// 用量限制信息
					double _minimumValue; // 用量限制最小值
					double _maximumValue; // 用量限制最大值
					Map<MedcineDTO, Integer> _reviewResult = new HashMap<MedcineDTO, Integer>();

					for (MedcineDTO medcineDTO : medcineDTOs) // 当前处方中的所有药品信息
					{
						_sameMedcineCounter = 0;
						_totalDoseSum = 0; // 每种不同的药品在开始审核之前，清零
						_totalDoseSum = medcineDTO.getTotal(); // 当前处方中一种药品开的总量

						// 计算medcineDTO Z天内所有处方的总剂量之和
						for (MedcineDTO medcineDTO2 : historyMedcineDTOsTotal)
						{
							if (medcineDTO.getCommonName().equals(medcineDTO2.getCommonName()) && medcineDTO.getSpecification().equals(medcineDTO2.getSpecification()))
							{
								_sameMedcineCounter++;
								_totalDoseSum += medcineDTO2.getTotal();
							}
						}

						if (_sameMedcineCounter > 0) // 历史处方中至少有一个相同药物
						{
							// 获取medcineDTO(审方系统传输对象) 对应的 medcineId(平台药品信息表中的id)
							for (Medcine medcine : medcines)
							{
								if (medcineDTO.getCommonName().equals(medcine.getName()) && auditCenter.getId() == medcine.getStandardSource() && medcineDTO.getSpecification() == medcine.getSpecification())
								{
									_medcineId = medcine.getId(); // 获取medcineId
									break;
								}
							}

							for (DosageLimit dosageLimitObj : dosageLimits)
							{
								if (_medcineId == dosageLimitObj.getMedcineId())
								{
									if (dosageLimitObj.getRelationalOperatorPre() == 1) // 第一个操作符为">"
									{
										if (dosageLimitObj.getRelationalOperatorPost() == 1) // 第一个操作符为">" ，并且第二个操作符为">"
										{
											_maximumValue = dosageLimitObj.getDosagePre();
											_minimumValue = dosageLimitObj.getDoesagePost();
											if (_maximumValue > _totalDoseSum && _totalDoseSum > _minimumValue)
											{
												// auditResults.put(auditRuleObj.getCode(), dosageLimitObj.getLevel());
												_reviewResult.put(medcineDTO, dosageLimitObj.getLevel());
												break;
											}
										}
										if (dosageLimitObj.getRelationalOperatorPost() == 2)// 第一个操作符为">" ，并且第二个操作符为">="
										{
											_maximumValue = dosageLimitObj.getDosagePre();
											_minimumValue = dosageLimitObj.getDoesagePost();
											if (_maximumValue > _totalDoseSum && _totalDoseSum >= _minimumValue)
											{
												// auditResults.put(auditRuleObj.getCode(), dosageLimitObj.getLevel());
												_reviewResult.put(medcineDTO, dosageLimitObj.getLevel());
												break;
											}
										}
										if (dosageLimitObj.getRelationalOperatorPost() == 3)// 第一个操作符为">" ，并且第二个操作符为"<"
										{
											continue;
										}
										if (dosageLimitObj.getRelationalOperatorPost() == 4)// 第一个操作符为">" ，并且第二个操作符为"<="
										{
											continue;
										}
									}
									else if (dosageLimitObj.getRelationalOperatorPre() == 2)// 第一个操作符为">="
									{
										if (dosageLimitObj.getRelationalOperatorPost() == 1)// 第一个操作符为">=" ，并且第二个操作符为">"
										{
											_maximumValue = dosageLimitObj.getDosagePre();
											_minimumValue = dosageLimitObj.getDoesagePost();
											if (_maximumValue >= _totalDoseSum && _totalDoseSum > _minimumValue)
											{
												// auditResults.put(auditRuleObj.getCode(), dosageLimitObj.getLevel());
												_reviewResult.put(medcineDTO, dosageLimitObj.getLevel());
												break;
											}
										}
										if (dosageLimitObj.getRelationalOperatorPost() == 2)// 第一个操作符为">="，并且第二个操作符为">="
										{
											_maximumValue = dosageLimitObj.getDosagePre();
											_minimumValue = dosageLimitObj.getDoesagePost();
											if (_maximumValue >= _totalDoseSum && _totalDoseSum >= _minimumValue)
											{
												// auditResults.put(auditRuleObj.getCode(), dosageLimitObj.getLevel());
												_reviewResult.put(medcineDTO, dosageLimitObj.getLevel());
												break;
											}
										}
										if (dosageLimitObj.getRelationalOperatorPost() == 3)// 第一个操作符为">=" ，并且第二个操作符为"<"
										{
											continue;

										}
										if (dosageLimitObj.getRelationalOperatorPost() == 4)// 第一个操作符为">="，并且第二个操作符为"<="
										{
											continue;
										}
									}
									else if (dosageLimitObj.getRelationalOperatorPre() == 3)// 第一个操作符为"<"
									{
										if (dosageLimitObj.getRelationalOperatorPost() == 1)// 第一个操作符为"<" ，并且第二个操作符为">"
										{
											continue;
										}
										if (dosageLimitObj.getRelationalOperatorPost() == 2)// 第一个操作符为"<" ，并且第二个操作符为">="
										{
											continue;
										}
										if (dosageLimitObj.getRelationalOperatorPost() == 3)// 第一个操作符为"<" ，并且第二个操作符为"<"
										{
											_minimumValue = dosageLimitObj.getDosagePre();
											_maximumValue = dosageLimitObj.getDoesagePost();
											if (_maximumValue > _totalDoseSum && _totalDoseSum > _minimumValue)
											{
												// auditResults.put(auditRuleObj.getCode(), dosageLimitObj.getLevel());
												_reviewResult.put(medcineDTO, dosageLimitObj.getLevel());
												break;
											}
										}
										if (dosageLimitObj.getRelationalOperatorPost() == 4)// 第一个操作符为"<" ，并且第二个操作符为"<="
										{
											_minimumValue = dosageLimitObj.getDosagePre();
											_maximumValue = dosageLimitObj.getDoesagePost();
											if (_maximumValue >= _totalDoseSum && _totalDoseSum > _minimumValue)
											{
												// auditResults.put(auditRuleObj.getCode(), dosageLimitObj.getLevel());
												_reviewResult.put(medcineDTO, dosageLimitObj.getLevel());
												break;
											}
										}
									}
									else if (dosageLimitObj.getRelationalOperatorPre() == 4)// 第一个操作符为"<="
									{
										if (dosageLimitObj.getRelationalOperatorPost() == 1)// 第一个操作符为"<=" ，并且第二个操作符为">"
										{
											continue;
										}
										if (dosageLimitObj.getRelationalOperatorPost() == 2)// 第一个操作符为"<="，并且第二个操作符为">="
										{
											continue;
										}
										if (dosageLimitObj.getRelationalOperatorPost() == 3)// 第一个操作符为"<=" ，并且第二个操作符为"<"
										{
											_minimumValue = dosageLimitObj.getDosagePre();
											_maximumValue = dosageLimitObj.getDoesagePost();
											if (_maximumValue > _totalDoseSum && _totalDoseSum >= _minimumValue)
											{
												// auditResults.put(auditRuleObj.getCode(), dosageLimitObj.getLevel());
												_reviewResult.put(medcineDTO, dosageLimitObj.getLevel());
												break;
											}
										}
										if (dosageLimitObj.getRelationalOperatorPost() == 4)// 第一个操作符为"<="，并且第二个操作符为"<="
										{
											_minimumValue = dosageLimitObj.getDosagePre();
											_maximumValue = dosageLimitObj.getDoesagePost();
											if (_maximumValue >= _totalDoseSum && _totalDoseSum >= _minimumValue)
											{
												// auditResults.put(auditRuleObj.getCode(), dosageLimitObj.getLevel());
												_reviewResult.put(medcineDTO, dosageLimitObj.getLevel());
												break;
											}
										}
									}
									else
									{
										continue;
									}

								}
							}
						}
					}

					// 打回
					if (_reviewResult.containsValue(4))
					{
						auditResults.put(auditRuleObj.getCode(), 4);

					}
					// 人工审核
					else if (_reviewResult.containsValue(3))
					{
						auditResults.put(auditRuleObj.getCode(), 3);

					}
					// 警告
					else if (_reviewResult.containsValue(2))
					{
						auditResults.put(auditRuleObj.getCode(), 2);

					}

				}
			}

			// 0003 超多日用量异常： Z天内同一个人的同一个药物的用药天数大于最大使用天数
			if ("超多日用量异常".equals(auditRuleObj.getName().trim()))
			{
				if (auditRuleObj.getAction() == 1)
				{
//TODO
					int _totalDuration = 0; // 历史用药天数总和
					boolean _isExist = false; // 当前处方中的药物，在历史处方中是否存在
					RList<DurationLimit> durationLimits = redissonClient.getList("durationLimits"); // 给药持续时间限制
					Map<Object, Object> _result = new HashMap<Object, Object>();

					// 判断药物在历史处方中是否存在
					for (MedcineDTO medcineDTOObj : medcineDTOs)
					{
						for (MedcineDTO historyMedcineDTOObj : historyMedcineDTOsTotal)
						{
							if (medcineDTOObj.getCommonName().equals(historyMedcineDTOObj.getCommonName()) && medcineDTOObj.getSpecification().equals(historyMedcineDTOObj.getSpecification()))
							{
								_isExist = true;
								break;
							}
						}
					}

					if (_isExist) // 历史处方存在相同药物
					{
						for (MedcineDTO medcineDTOObj : medcineDTOs) // 当前处方所有药物
						{
							_totalDuration = medcineDTOObj.getDuration(); // 当前处方的用药天数累加到用药总天数
							int _medcineId = 0;

							// 获取药物对应的药品id
							for (Medcine medcineObj : medcines)
							{
								if (medcineDTOObj.getCommonName().equals(medcineObj.getName()))
								{
									if (medcineDTOObj.getSpecification().equals(medcineObj.getSpecification()))
									{
										if (auditCenter.getId() == medcineObj.getStandardSource())
										{
											_medcineId = medcineObj.getId();
										}
									}

								}
							}

							for (MedcineDTO historyMdecineDTOObj : historyMedcineDTOsTotal) // 历史处方所有药物
							{
								if (medcineDTOObj.getCommonName().equals(historyMdecineDTOObj.getCommonName())) // 通用名相同
								{
									if (medcineDTOObj.getSpecification().equals(historyMdecineDTOObj.getSpecification())) // 规格相同
									{
										_totalDuration += historyMdecineDTOObj.getDuration(); // 发现同一个药物，累加用药天数
									}
								}
							}

							if (_medcineId > 0)
							{
								for (DurationLimit durationLimitObj : durationLimits)
								{
									if (_medcineId == durationLimitObj.getMedcineId())
									{
										if (_totalDuration >= durationLimitObj.getLowerBound() && _totalDuration <= durationLimitObj.getUpperBound())
										{
											_result.put(medcineDTOObj, durationLimitObj.getLevel());
											break;
										}
									}
								}
							}

							// 打回
							if (_result.containsValue(4))
							{
								auditResults.put(auditRuleObj.getCode(), 4);

							}
							// 人工审核
							else if (_result.containsValue(3))
							{
								auditResults.put(auditRuleObj.getCode(), 3);

							}
							// 警告
							else if (_result.containsValue(2))
							{
								auditResults.put(auditRuleObj.getCode(), 2);

							}

						}

					}

				}
			}

			// 0004 配伍禁忌：Z天内所有处方（包含历史处方和本次处方）中A和B药物一起使用会产生不良反应。 所用规则来源于数据维护中规则管理的“药物配伍管理”功能。
			if ("配伍禁忌".equals(auditRuleObj.getName().trim()))
			{
				if (auditRuleObj.getAction() == 1)
				{

				}
			}

			// 0005 相互作用：所有处方（包含历史处方和本次处方）中A药与B药相互作用，产生好的反应（提示使）
			// 所用规则来源于数据维护中规则管理的“相互作用管理”功能。
			if ("相互作用".equals(auditRuleObj.getName().trim()))
			{
				if (auditRuleObj.getAction() == 1)
				{

				}
			}

			//TODO 0006 诊断与药品限用：Z天内所有处方中的诊断和药品的限用。所用规则来源于数据维护中规则管理的“诊断与药品限制”功能。
			if ("诊断与药品限用".equals(auditRuleObj.getName().trim()))
			{
				if (auditRuleObj.getAction() == 1)
				{
					Statement st = ServerObj.getConnection().createStatement();
					ResultSet rs = null;
					
					StringBuffer sbDiagnosisIds = null;
					List<DiagnosisDTO> DiagnosisDTOList = prescriptionDTO.getDiagnosis();//本次处方的诊断信息
					if(DiagnosisDTOList.size() == 0) {
						auditResults.put(auditRuleObj.getCode(), 4);//本次处方没有诊断信息则打回
					}
					for(DiagnosisDTO diagnosisDTO : DiagnosisDTOList){
						rs = st.executeQuery("SELECT id FROM t_diagnosis WHERE icd10='" + diagnosisDTO.getIcd10() + "'");
						if(!sbDiagnosisIds.equals(null))
							sbDiagnosisIds.append(",");
						sbDiagnosisIds.append(rs.getInt(1));
					}
					//本次处方限用药品ID集合
					rs = st.executeQuery("SELECT medcine_id FROM t_medcine_restriction WHERE diagnosis_id IN(" + sbDiagnosisIds + ")");
						
					
				}
			}

			// 0007 检查与药品：Z天内所有检查报告，根据“检查限用”模块中的规则审核处方中的药物。 检查报告数值的判断与分析
			if ("检查与药品".equals(auditRuleObj.getName().trim()))
			{
				if (auditRuleObj.getAction() == 1)
				{

				}
			}

		}

		prescriptionVO.setAuditCenter(auditCenter.getId()); // 审方中心
		prescriptionVO.setAuditPharmacist(prescriptionDTO.getAuditPharmacist()); // 审核药师
		prescriptionVO.setAuditTime(prescriptionDTO.getAuditTime()); // 审核时间
		prescriptionVO.setComfirmPharmacist(prescriptionDTO.getComfirmPharmacist()); // 确认药师
		prescriptionVO.setComfirmTime(prescriptionDTO.getComfirmTime()); // 确认时间
		prescriptionVO.setDenyLevel(prescriptionDTO.getDenyLevel()); // 打回等级
		prescriptionVO.setDenyOwner(prescriptionDTO.getDenyOwner()); // 打回对象
		prescriptionVO.setDenyReason(prescriptionDTO.getDenyReason()); // 打回原因
		prescriptionVO.setDepartmentName(prescriptionDTO.getDepartmentName()); // 科室
		prescriptionVO.setDoctorName(prescriptionDTO.getDoctorName()); // 医生
		prescriptionVO.setMedicalUnit(unitName.getId()); // 医院名称
		prescriptionVO.setOverTime(false); // 是否超时
		prescriptionVO.setPatientInfo(prescriptionDTO.getPatientInfo()); // 病人信息
		prescriptionVO.setPrescriptionContent(prescriptionDTO.getPrescriptionContent()); // 处方内容
		prescriptionVO.setDiagnosis(prescriptionDTO.getDiagnosis()); // 诊断信息
		prescriptionVO.setMedcineItems(prescriptionDTO.getMedcineItems()); // 药品信息
		prescriptionVO.setPrescriptionNumber(prescriptionDTO.getPrescriptionNumber()); // 处方号码
		prescriptionVO.setIsEmergency(prescriptionDTO.getIsEmergency()); // 是否急诊科室
		prescriptionVO.setPrescriptionProblemCodes(auditResults);
		prescriptionVO.setPrescriptionReason(prescriptionDTO.getPrescriptionReason());

		// 打回
		if (auditResults.containsValue(4))
		{
			prescriptionVO.setReviewState(4);
		}
		// 人工审核
		else if (auditResults.containsValue(3))
		{
			prescriptionVO.setReviewState(3);
		}
		// 警告
		else if (auditResults.containsValue(2))
		{
			prescriptionVO.setReviewState(2);
		}
		// 通过
		else
		{
			prescriptionVO.setReviewState(1);
		}

		prescriptionVO.setState(prescriptionDTO.getState());
		prescriptionVO.setSubmmitTime(prescriptionDTO.getSubmmitTime());
		prescriptionVO.setTotalFee(prescriptionDTO.getTotalFee());
		prescriptionVO.setType(prescriptionDTO.getType());
		prescriptionVO.setVisitNumber(prescriptionDTO.getVisitNumber());
		prescriptionVOs.add(prescriptionVO);

		if (!redissonClient.isShutdown())
		{
			if (!redissonClient.isShuttingDown())
			{
				redissonClient.shutdown();
			}
		}

		if (prescriptionVO.getReviewState() == 4 || prescriptionVO.getReviewState() == 3)
		{
			ServerObj.notPassPrescriptionIncrementIn3Seconds++; // 复审结果为未通过（打回或人工审核），复审未通过数加一
		}

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception
	{
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		System.out.println(cause.getMessage());
		ctx.close();
		// super.exceptionCaught(ctx, cause);
	}
}
