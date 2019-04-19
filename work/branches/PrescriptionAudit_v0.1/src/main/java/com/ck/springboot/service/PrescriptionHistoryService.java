package com.ck.springboot.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ck.springboot.pojo.PrescriptionHistory;
import com.ck.springboot.pojo.PrescriptionHistoryAndPatient;
import com.ck.springboot.pojo.PrescriptionHistoryDetail;
import com.ck.springboot.pojo.PrescriptionHistoryPending;
import com.ck.springboot.pojo.PrescriptionHistorySee;


@Service
@Transactional
public interface PrescriptionHistoryService {
	public List<PrescriptionHistory> getAllPrescriptionHistory(Integer pageNo,Integer pageSize,
			Integer auditCenterId,Integer doctorId,Integer auditPharmacistId,String patientName,
			Integer typeId,String problemCode,Integer diagnosisId,
			Integer stateId,Integer reviewStateId,Integer deptId,String beginDateScope);
	
	public int getPrescriptionHistoryCount(Integer auditCenterId,Integer doctorId,Integer auditPharmacistId,String patientName,
			Integer typeId,String problemCode,Integer diagnosisId,
			Integer stateId,Integer reviewStateId,Integer deptId,String beginDateScope);
	
	public List<PrescriptionHistorySee> getAllPrescriptionHistorySeeT(Integer pageNo,Integer pageSize,
			Integer auditCenterId,Integer reviewStateId,
			String prescriptionNumber,String beginDateScope) throws IOException;
	
	public List<PrescriptionHistorySee> getAllPrescriptionHistorySeeF(Integer pageNo,Integer pageSize,
			Integer auditCenterId,Integer reviewStateId,
			String prescriptionNumber,String beginDateScope);

	
	public int getPrescriptionHistoryCountF(Integer auditCenterId, Integer reviewStateId,
			String prescriptionNumber, String beginDateScope);//问题处方
	
	public int getPrescriptionHistoryCountT(Integer auditCenterId, Integer reviewStateId,
			String prescriptionNumber, String beginDateScope);//所有处方

	public List<PrescriptionHistoryPending> getPendingHistory(Integer pageNo,Integer pageSize,
			Integer auditCenterId,Integer prescriptionNumber,Integer reviewStateId,
			 String typeId,String beginDateScope);//待处理处方
	
	public int getPendingHistoryCount(Integer auditCenterId,Integer prescriptionNumber,
			Integer reviewStateId,String typeId,String beginDateScope);//待处理处方数量
	
	public List<PrescriptionHistoryDetail> getPreHisById(Integer id) throws IOException;//通过处方主键查询处方详情
	
	//查询病人的历史处方
	public List<PrescriptionHistoryAndPatient> getPrescriptionHistoryAndPatient(String idNumber,String socialSecurityNumber,
			Timestamp nowDate,Timestamp beforeDate,
			Timestamp dTimeBefore,Timestamp dTimeLater);
	
	//查询历史审核天数
	public int getHistoryCheckDays();
	
	
	//根据处方号和身份证号查询问题处方的数量
		public int getCountsByIdNumber(String idNumber,
				String prescriptionNumber);
		
		//根据处方号和社保号查询问题处方的数量
		public int getCountsBySocialSecurityNumber(String socialSecurityNumber,
				String prescriptionNumber);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
