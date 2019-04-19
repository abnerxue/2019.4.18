package com.ck.springboot.pojo;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PrescriptionHistory {
	private int id;
	private String state;//初审状态(超时通过，自动通过，警告，单签通过，双签通过，人工打回，自动打回)
	private String reviewState;//复审处理状态(超时通过，自动通过，警告，单签通过，双签通过，人工打回，自动打回)
	private String prescriptionNumber;//处方号
	private String prescriptionContent;//处方内容
//	private String problemCode;	//问题代码(外键)
	private String prescriptionReason;//用药理由
	private String visitNumber;//就诊号
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date submmitTime;//提交时间
	private String auditPharmacist;//审核药师
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date auditTime;//审核时间
	private int comfirmPharmacist;//确认药师ID
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date comfirmTime;//确认时间
	private int type;//处方类型(中药，西药，中成药)
	private int auditCenter;//审方中心
	private int medicalUnit;//就医单位名称
	private String denyReason;//打回原因
	private String denyOwner;//打回对象
	private String denyLevel;//打回等级
	private String doctorId;//医生ID
	private int deptId;//科室id(外键)
	private int patientId;//病人ID
	private double totalFee;//合计金额
//	private int diagnosisId;//诊断ID
	
	private AuditState auditstate;
//	private String stateName;
//	private String reviewName;
	
//	private AuditRules  auditrules;
//	private String problemName;
	
	
	private HospitalStaff hospitalstaff;
//	private String auditPharmacistName;
//	private String comfirmPharmacistName;
//	private String doctorName;
	
	private Type typedic;
//	private String typeName;
	
	
	private AuditCenter auditcenter;
//	private String auditCenterName;
	
	private UnitName unitname;
//	private String unitName;
	
	
	private MedicalDept medicaldept;
//	private String deptName;
	
	private Patient patient;
//	private String patientName;
	
	
//	private Diagnosis diagnosis;
//	private String diagnosisName;
	
	
	private List<ProblemCode> problemcode;
	
	private List<PrescriptionDiagnosis> diagnosis;

//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getState() {
//		return state;
//	}
//	public void setState(String state) {
//		this.state = state;
//	}
//	public String getReviewState() {
//		return reviewState;
//	}
//	public void setReviewState(String reviewState) {
//		this.reviewState = reviewState;
//	}
//	public int getPrescriptionNumber() {
//		return prescriptionNumber;
//	}
//	public void setPrescriptionNumber(int prescriptionNumber) {
//		this.prescriptionNumber = prescriptionNumber;
//	}
//	public String getPrescriptionContent() {
//		return prescriptionContent;
//	}
//	public void setPrescriptionContent(String prescriptionContent) {
//		this.prescriptionContent = prescriptionContent;
//	}
//	public String getproblemCode() {
//		return problemCode;
//	}
//	public void setproblemCode(String problemCode) {
//		this.problemCode = problemCode;
//	}
//	public String getPrescriptionReason() {
//		return prescriptionReason;
//	}
//	public void setPrescriptionReason(String prescriptionReason) {
//		this.prescriptionReason = prescriptionReason;
//	}
//	public String getPatientName() {
//		return patient.getPatientName();
//	}
//	public void setPatientName(String patientName) {
//		this.patientName = patientName;
//	}
//	public String getVisitNumber() {
//		return visitNumber;
//	}
//	public void setVisitNumber(String visitNumber) {
//		this.visitNumber = visitNumber;
//	}
//	public Date getSubmmitTime() {
//		return submmitTime;
//	}
//	public void setSubmmitTime(Date submmitTime) {
//		this.submmitTime = submmitTime;
//	}
//	public String getAuditPharmacist() {
//		return auditPharmacist;
//	}
//	public void setAuditPharmacist(String auditPharmacist) {
//		this.auditPharmacist = auditPharmacist;
//	}
//	public Date getAuditTime() {
//		return auditTime;
//	}
//	public void setAuditTime(Date auditTime) {
//		this.auditTime = auditTime;
//	}
//	public int getComfirmPharmacist() {
//		return comfirmPharmacist;
//	}
//	public void setComfirmPharmacist(int comfirmPharmacist) {
//		this.comfirmPharmacist = comfirmPharmacist;
//	}
//	public Date getComfirmTime() {
//		return comfirmTime;
//	}
//	public void setComfirmTime(Date comfirmTime) {
//		this.comfirmTime = comfirmTime;
//	}
//	public int getType() {
//		return type;
//	}
//	public void setType(int type) {
//		this.type = type;
//	}
//	public int getAuditCenter() {
//		return auditCenter;
//	}
//	public void setAuditCenter(int auditCenter) {
//		this.auditCenter = auditCenter;
//	}
//	public int getMedicalUnit() {
//		return medicalUnit;
//	}
//	public void setMedicalUnit(int medicalUnit) {
//		this.medicalUnit = medicalUnit;
//	}
//	public String getDenyReason() {
//		return denyReason;
//	}
//	public void setDenyReason(String denyReason) {
//		this.denyReason = denyReason;
//	}
//	public String getDenyOwner() {
//		return denyOwner;
//	}
//	public void setDenyOwner(String denyOwner) {
//		this.denyOwner = denyOwner;
//	}
//	public String getDenyLevel() {
//		return denyLevel;
//	}
//	public void setDenyLevel(String denyLevel) {
//		this.denyLevel = denyLevel;
//	}
//	public String getDoctorId() {
//		return doctorId;
//	}
//	public void setDoctorId(String doctorId) {
//		this.doctorId = doctorId;
//	}
//	public int getDeptId() {
//		return deptId;
//	}
//	public void setDeptId(int deptId) {
//		this.deptId = deptId;
//	}
//	public int getPatientId() {
//		return patientId;
//	}
//	public void setPatientId(int patientId) {
//		this.patientId = patientId;
//	}
//	public double getTotalFee() {
//		return totalFee;
//	}
//	public void setTotalFee(double totalFee) {
//		this.totalFee = totalFee;
//	}
//	public int getDiagnosisId() {
//		return diagnosisId;
//	}
//	public void setDiagnosisId(int diagnosisId) {
//		this.diagnosisId = diagnosisId;
//	}
//	public String getStateName() {
//		return auditstate.getStateName();
//	}
//	public void setStateName(String stateName) {
//		this.stateName = stateName;
//	}
//	public String getReviewName() {
//		return auditstate.getReviewName();
//	}
//	public void setReviewName(String reviewName) {
//		this.reviewName = reviewName;
//	}
//	public String getProblemName() {
//		return auditrules.getProblemName();
//	}
//	public void setProblemName(String problemName) {
//		this.problemName = problemName;
//	}
//	public String getAuditPharmacistName() {
//		return hospitalstaff.getAuditPharmacistName();
//	}
//	public void setAuditPharmacistName(String auditPharmacistName) {
//		this.auditPharmacistName = auditPharmacistName;
//	}
//	public String getComfirmPharmacistName() {
//		return hospitalstaff.getComfirmPharmacistName();
//	}
//	public void setComfirmPharmacistName(String comfirmPharmacistName) {
//		this.comfirmPharmacistName = comfirmPharmacistName;
//	}
//	
//	public String getDoctorName() {
//		return hospitalstaff.getDoctorName();
//	}
//	public void setDoctorName(String doctorName) {
//		this.doctorName = doctorName;
//	}
//	public String getTypeName() {
//		return typedic.getTypeName();
//	}
//	public void setTypeName(String typeName) {
//		this.typeName = typeName;
//	}
//
//	public String getAuditCenterName() {
//		return auditcenter.getAuditCenterName();
//	}
//	public void setAuditCenterName(String auditCenterName) {
//		this.auditCenterName = auditCenterName;
//	}
//	public String getUnitName() {
//		return unitname.getUnitName();
//	}
//	public void setUnitName(String unitName) {
//		this.unitName = unitName;
//	}
//	public String getDeptName() {
//		return medicaldept.getDeptName();
//	}
//	public void setDeptName(String deptName) {
//		this.deptName = deptName;
//	}
//	public String getDiagnosisName() {
//		return diagnosis.getDiagnosisName();
//	}
//	public void setDiagnosisName(String diagnosisName) {
//		this.diagnosisName = diagnosisName;
//	}

}
