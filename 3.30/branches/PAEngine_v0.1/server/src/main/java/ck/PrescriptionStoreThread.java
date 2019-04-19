package ck;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

import ck.dto.DiagnosisDTO;
import ck.dto.MedcineDTO;
import ck.po.Diagnosis;
import ck.po.HospitalStaff;
import ck.po.Medcine;
import ck.po.MedicalDept;
import ck.vo.PrescriptionVO;

public class PrescriptionStoreThread extends Thread
{

	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(1000 * 60 * 10);
				// TODO get data from redis and store to DB
				org.redisson.config.Config redissonConfig = new org.redisson.config.Config();
				// redissonConfig.setCodec(new org.redisson.client.codec.StringCodec());
//				redissonConfig.useSingleServer().setAddress("redis://192.168.0.144:6379");
				redissonConfig.useSingleServer().setAddress("redis://192.168.0.7:6379");
				redissonConfig.useSingleServer().setAddress("redis://localhost:6379");
				RedissonClient myRedissonClient = Redisson.create(redissonConfig);
				// 处方列表
				List<PrescriptionVO> prescriptionVOs = myRedissonClient.getList("prescriptionVOs");
				// 获取处方列表第一条处方信息
				if (prescriptionVOs.size() > 0)
				{
					PrescriptionVO prescriptionVO = prescriptionVOs.get(0);
					Connection connection = ServerObj.getConnection();
					PreparedStatement myPreparedStatement = null;
					String sql = null;
					try
					{
						connection.setAutoCommit(false);
						// 将第一条处方信息包含的病人信息保存到t_patient
						sql = "INSERT INTO t_patient (name, gender, age, address, allergy_history, weight, birthday) VALUES (?, ?, ?, ?, ?, ?, ?)";
						myPreparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
						myPreparedStatement.setString(1, prescriptionVO.getPatientInfo().getName());
						myPreparedStatement.setBoolean(2, prescriptionVO.getPatientInfo().getGender());
						myPreparedStatement.setInt(3, prescriptionVO.getPatientInfo().getAge());
						myPreparedStatement.setString(4, prescriptionVO.getPatientInfo().getAddress());
						myPreparedStatement.setString(5, prescriptionVO.getPatientInfo().getAllergyHistory());
						myPreparedStatement.setFloat(6, prescriptionVO.getPatientInfo().getWeight());
						myPreparedStatement.setString(7, prescriptionVO.getPatientInfo().getBirthday());
						myPreparedStatement.execute();
						ResultSet rs = myPreparedStatement.getGeneratedKeys();
						int patientId = 0;
						while (rs.next())
						{
							patientId = rs.getInt(1);
						}
						rs.close();

						// 查询doctorId,deptId
						RList<HospitalStaff> hospitalStaffList = myRedissonClient.getList("hospitalStaffList");
						RList<MedicalDept> medicalDeptList = myRedissonClient.getList("medicalDeptList");
						int hospitalId = prescriptionVO.getMedicalUnit();
						String deptName = prescriptionVO.getDepartmentName();
						int deptId = -1;
						String doctorName = prescriptionVO.getDoctorName();
						int docId = -1;
						for (MedicalDept medicalDept : medicalDeptList)
						{
							if (hospitalId == medicalDept.getHospitalId() && deptName.equals(medicalDept.getName()))
							{
								deptId = medicalDept.getId();
								break;
							}
						}
						for (HospitalStaff hospitalStaff : hospitalStaffList)
						{
							if (deptId == hospitalStaff.getMedicalDeptId() && doctorName.equals(hospitalStaff.getName()) && hospitalStaff.getType() == 1)
							{
								docId = hospitalStaff.getId();
								break;
							}
						}

						// 将第一条处方信息保存到t_prescription_history
						sql = "INSERT INTO dbo.t_prescription_history (state, review_state, prescription_number, prescription_content, prescription_reason, visit_number, submmit_time, audit_pharmacist, audit_time, comfirm_pharmacist, comfirm_time, type, audit_center, medical_unit, deny_reason, deny_owner, deny_level, doctor_id, dept_id, patient_id, total_fee, over_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						myPreparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
						myPreparedStatement.setInt(1, prescriptionVO.getState());
						myPreparedStatement.setInt(2, prescriptionVO.getReviewState());
						myPreparedStatement.setString(3, String.valueOf(prescriptionVO.getPrescriptionNumber()));
						myPreparedStatement.setString(4, prescriptionVO.getPrescriptionContent());
						myPreparedStatement.setString(5, prescriptionVO.getPrescriptionReason());
						myPreparedStatement.setString(6, prescriptionVO.getVisitNumber());
						myPreparedStatement.setDate(7, new Date(prescriptionVO.getSubmmitTime().getTime()));
						myPreparedStatement.setInt(8, Integer.valueOf(prescriptionVO.getAuditPharmacist()));
						myPreparedStatement.setDate(9, new Date(prescriptionVO.getAuditTime().getTime()));
						myPreparedStatement.setInt(10, Integer.valueOf(prescriptionVO.getComfirmPharmacist()));
						myPreparedStatement.setDate(11, new Date(prescriptionVO.getComfirmTime().getTime()));
						myPreparedStatement.setInt(12, prescriptionVO.getType());
						myPreparedStatement.setInt(13, prescriptionVO.getAuditCenter());
						myPreparedStatement.setInt(14, prescriptionVO.getMedicalUnit());
						myPreparedStatement.setString(15, prescriptionVO.getDenyReason());
						myPreparedStatement.setString(16, prescriptionVO.getDenyOwner());
						myPreparedStatement.setString(17, prescriptionVO.getDenyLevel());
						myPreparedStatement.setInt(18, docId);
						myPreparedStatement.setInt(19, deptId);
						myPreparedStatement.setInt(20, patientId);
						myPreparedStatement.setBigDecimal(21, BigDecimal.valueOf(prescriptionVO.getTotalFee()));
						myPreparedStatement.setBoolean(22, false);
						myPreparedStatement.execute();
						ResultSet rsPrescriptionHistory = myPreparedStatement.getGeneratedKeys();
						int prescriptionHistoryId = -1;
						while (rsPrescriptionHistory.next())
						{
							prescriptionHistoryId = rsPrescriptionHistory.getInt(1);
						}
						rsPrescriptionHistory.close();
						// 查询药品id
						RList<Medcine> medcinesList = myRedissonClient.getList("medcinesList");

						// 新增处方对应的药品信息
						sql = "INSERT INTO t_prescription_medcine (prescription_history_id, medcine_id, total, specification) VALUES (?, ?, ?, ?)";
						myPreparedStatement = connection.prepareStatement(sql);
						List<MedcineDTO> medcineItems = prescriptionVO.getMedcineItems();
						for (MedcineDTO medcineItem : medcineItems)
						{
							myPreparedStatement.setInt(1, prescriptionHistoryId);
							for (Medcine medcine : medcinesList)
							{
								if (medcineItem.getCommonName().equals(medcine.getName()) && medcineItem.getSpecification().equals(medcine.getSpecification()) && prescriptionVO.getMedicalUnit() == medcine.getStandardSource())
								{
									myPreparedStatement.setInt(2, medcine.getId());
									break;
								}
							}

							myPreparedStatement.setString(3, String.valueOf(medcineItem.getTotal()));
							myPreparedStatement.setString(4, medcineItem.getSpecification());
							myPreparedStatement.executeUpdate();
						}

						// get diagnosis_id
						RList<Diagnosis> diagnosisList = myRedissonClient.getList("diagnosisList");

						// 新增处方对应的诊断信息
						sql = "INSERT INTO t_prescription_diagnosis (prescription_history_id, diagnosis_id) VALUES (?, ?)";
						myPreparedStatement = connection.prepareStatement(sql);
						List<DiagnosisDTO> diagnosisDTOList = prescriptionVO.getDiagnosis();
						for (DiagnosisDTO diagnosisDTO : diagnosisDTOList)
						{
							myPreparedStatement.setInt(1, prescriptionHistoryId);
							for (Diagnosis diagnosis : diagnosisList)
							{
								if (diagnosisDTO.getDiagnosisName().equals(diagnosis.getDiagnosisName()) && diagnosisDTO.getIcd10().equals(diagnosis.getIcd10()))
								{
									myPreparedStatement.setInt(2, diagnosis.getId());
									break;
								}
							}
							myPreparedStatement.executeUpdate();
						}

						// add data to t_prescription_problem_code
						sql = "INSERT INTO dbo.t_prescription_problem_code (prescription_history_id, problem_code) VALUES (?, ?)";
						myPreparedStatement = connection.prepareStatement(sql);
						Map<String, Integer> prescriptionProblemCodes = prescriptionVO.getPrescriptionProblemCodes();
						for (Entry<String, Integer> entry : prescriptionProblemCodes.entrySet())
						{
							myPreparedStatement.setInt(1, prescriptionHistoryId);
							myPreparedStatement.setString(2, entry.getKey());
							myPreparedStatement.execute();
						}

						connection.commit();
						rs.close();
						myPreparedStatement.close();
						connection.close();
						prescriptionVOs.remove(0);
						myRedissonClient.shutdown();

					}
					catch (SQLException e1)
					{
						try
						{
							connection.rollback();
						}
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						e1.printStackTrace();
					}

				}

				if (!myRedissonClient.isShutdown())
				{
					if (!myRedissonClient.isShuttingDown())
					{
						myRedissonClient.shutdown();
					}
				}

			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}