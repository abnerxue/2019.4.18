package com.ck.springboot.mapper;

import com.ck.springboot.pojo.*;
import com.ck.springboot.pojo.report.PrescriptionHistoryReportDetail;
import com.ck.springboot.pojo.report.reportSummary;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface PrescriptionHistoryMapper {

	public List<PrescriptionHistory> getAllPrescriptionHistory(@Param("pageNo") Integer pageNo,
                                                               @Param("pageSize") Integer pageSize, @Param("auditCenterId") Integer auditCenterId, @Param("doctorId") Integer doctorId,
                                                               @Param("auditPharmacistId") Integer auditPharmacistId, @Param("patientName") String patientName,
                                                               @Param("typeId") Integer typeId, @Param("problemCode") String problemCode,
                                                               @Param("diagnosisId") Integer diagnosisId, @Param("stateId") Integer stateId,
                                                               @Param("reviewStateId") Integer reviewStateId, @Param("deptId") Integer deptId,
                                                               @Param("startBeginDate") String startBeginDate, @Param("endBeginDate") String endBeginDate);

	public List<PrescriptionHistorySee> getAllPrescriptionHistorySeeT(@Param("pageNo") Integer pageNo,
                                                                      @Param("pageSize") Integer pageSize, @Param("auditCenterId") Integer auditCenterId,
                                                                      @Param("reviewStateId") Integer reviewStateId, @Param("prescriptionNumber") String prescriptionNumber,
                                                                      @Param("startBeginDate") String startBeginDate, @Param("endBeginDate") String endBeginDate);

	public List<PrescriptionHistorySee> getAllPrescriptionHistorySeeF(@Param("pageNo") Integer pageNo,
                                                                      @Param("pageSize") Integer pageSize, @Param("auditCenterId") Integer auditCenterId,
                                                                      @Param("reviewStateId") Integer reviewStateId, @Param("prescriptionNumber") String prescriptionNumber,
                                                                      @Param("startBeginDate") String startBeginDate, @Param("endBeginDate") String endBeginDate);

	public int getPrescriptionHistoryCount(@Param("auditCenterId") Integer auditCenterId, @Param("doctorId") Integer doctorId,
                                           @Param("auditPharmacistId") Integer auditPharmacistId, @Param("patientName") String patientName,
                                           @Param("typeId") Integer typeId, @Param("problemCode") String problemCode,
                                           @Param("diagnosisId") Integer diagnosisId, @Param("stateId") Integer stateId,
                                           @Param("reviewStateId") Integer reviewStateId, @Param("deptId") Integer deptId,
                                           @Param("startBeginDate") String startBeginDate, @Param("endBeginDate") String endBeginDate);

	public int getPrescriptionHistoryCountT(
            @Param("auditCenterId") Integer auditCenterId,
            @Param("reviewStateId") Integer reviewStateId,
            @Param("prescriptionNumber") String prescriptionNumber,
            @Param("startBeginDate") String startBeginDate,
            @Param("endBeginDate") String endBeginDate);

	public int getPrescriptionHistoryCountF(
            @Param("auditCenterId") Integer auditCenterId,
            @Param("reviewStateId") Integer reviewStateId,
            @Param("prescriptionNumber") String prescriptionNumber,
            @Param("startBeginDate") String startBeginDate,
            @Param("endBeginDate") String endBeginDate);

	public List<PrescriptionHistoryPending> getPendingHistory(@Param("pageNo") Integer pageNo,
                                                              @Param("pageSize") Integer pageSize, @Param("auditCenterId") Integer auditCenterId,
                                                              @Param("prescriptionNumber") Integer prescriptionNumber,
                                                              @Param("reviewStateId") Integer reviewStateId,
                                                              @Param("typeId") String typeId,
                                                              @Param("startBeginDate") String startBeginDate,
                                                              @Param("endBeginDate") String endBeginDate);//待处理处方

	public int getPendingHistoryCount(@Param("auditCenterId") Integer auditCenterId, @Param("prescriptionNumber") Integer prescriptionNumber,
                                      @Param("reviewStateId") Integer reviewStateId,
                                      @Param("typeId") String typeId,
                                      @Param("startBeginDate") String startBeginDate,
                                      @Param("endBeginDate") String endBeginDate);//待处理处方数量

	public List<PrescriptionHistoryDetail> getPreHisById(@Param("id") Integer id);

	//查询病人的历史处方
	public List<PrescriptionHistoryAndPatient> getPrescriptionHistoryAndPatient(
            @Param("idNumber") String idNumber, @Param("socialSecurityNumber") String socialSecurityNumber,
            @Param("nowDate") Timestamp nowDate, @Param("beforeDate") Timestamp beforeDate,
            @Param("dTimeBefore") Timestamp dTimeBefore, @Param("dTimeLater") Timestamp dTimeLater);

	//查询历史审核天数
	public int getHistoryCheckDays();

	//根据处方号和身份证号查询问题处方的数量
	public int getCountsByIdNumber(@Param("idNumber") String idNumber,
                                   @Param("prescriptionNumber") String prescriptionNumber);

	//根据处方号和社保号查询问题处方的数量
	public int getCountsBySocialSecurityNumber(@Param("socialSecurityNumber") String socialSecurityNumber,
                                               @Param("prescriptionNumber") String prescriptionNumber);
	//根据时间段查询处方汇总信息
	public List<reportSummary> getReportSummary(@Param("startBeginDate") String startBeginDate, 
			@Param("endBeginDate") String endBeginDate);
	
	//根据时间段查询处方总数
	public int getReportSummaryCount(@Param("startBeginDate") String startBeginDate, 
			@Param("endBeginDate") String endBeginDate);
	
	//根据时间段查询处方汇总信息详情
	public List<PrescriptionHistoryReportDetail> getReportSummaryDetail(@Param("pageNo") Integer pageNo,
            @Param("pageSize") Integer pageSize,@Param("startBeginDate") String startBeginDate, 
			@Param("endBeginDate") String endBeginDate);
	
	//根据处方号查询问题列表
	public List<ProblemCode> getProblemById(@Param("prescriptionNumber") String prescriptionNumber);
	
}
