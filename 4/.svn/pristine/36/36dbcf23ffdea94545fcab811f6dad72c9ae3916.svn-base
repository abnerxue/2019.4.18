package com.ck.springboot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PrescriptionHistoryPending {
	private int id;
	private String state;//初审状态(超时通过，自动通过，警告，单签通过，双签通过，人工打回，自动打回)
	private String reviewState;//复审处理状态(超时通过，自动通过，警告，单签通过，双签通过，人工打回，自动打回)
	private int overTime;//审核超时
	private int deptId;//科室id(外键)
	private String visitNumber;//就诊号
	private int prescriptionNumber;//处方号
	private int type;//处方类型(中药，西药，中成药)
	private int patientId;//病人ID
	private int diagnosisId;//诊断ID
	private String prescriptionReason;//用药理由
	private int problemId;	//问题代码(外键)\
	
	private int maxDay;	//最大用药天数
	
	private AuditState auditstate;
//	private String stateName;
//	private String reviewName;
	
	private Patient patient;
//	private String patientName;
	
//	private AuditRules  auditrules;
//	private String problemName;
	private List<ProblemCode> problemcode;
	
	private List<PrescriptionDiagnosis> diagnosis;
//	private Diagnosis diagnosis;
//	private String diagnosisName;
	
	private MedicalDept medicaldept;
//	private String deptName;
	
	private Type typedic;
//	private String typeName;
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
//	public int getOverTime() {
//		return overTime;
//	}
//	public void setOverTime(int overTime) {
//		this.overTime = overTime;
//	}
//	public int getDeptId() {
//		return deptId;
//	}
//	public void setDeptId(int deptId) {
//		this.deptId = deptId;
//	}
//	public String getVisitNumber() {
//		return visitNumber;
//	}
//	public void setVisitNumber(String visitNumber) {
//		this.visitNumber = visitNumber;
//	}
//	public int getPrescriptionNumber() {
//		return prescriptionNumber;
//	}
//	public void setPrescriptionNumber(int prescriptionNumber) {
//		this.prescriptionNumber = prescriptionNumber;
//	}
//	public int getPatientId() {
//		return patientId;
//	}
//	public void setPatientId(int patientId) {
//		this.patientId = patientId;
//	}
//	public int getDiagnosisId() {
//		return diagnosisId;
//	}
//	public void setDiagnosisId(int diagnosisId) {
//		this.diagnosisId = diagnosisId;
//	}
//	public String getPrescriptionReason() {
//		return prescriptionReason;
//	}
//	public void setPrescriptionReason(String prescriptionReason) {
//		this.prescriptionReason = prescriptionReason;
//	}
//	public int getProblemId() {
//		return problemId;
//	}
//	public void setProblemId(int problemId) {
//		this.problemId = problemId;
//	}
//	
//	public int getMaxDay() {
//		return maxDay;
//	}
//	public void setMaxDay(int maxDay) {
//		this.maxDay = maxDay;
//	}
//	public int getType() {
//		return type;
//	}
//	public void setType(int type) {
//		this.type = type;
//	}
//	public String getPatientName() {
//		return patient.getPatientName();
//	}
//	public void setPatientName(String patientName) {
//		this.patientName = patientName;
//	}
//	public String getDiagnosisName() {
//		return diagnosis.getDiagnosisName();
//	}
//	public void setDiagnosisName(String diagnosisName) {
//		this.diagnosisName = diagnosisName;
//	}
//	public String getDeptName() {
//		return medicaldept.getDeptName();
//	}
//	public void setDeptName(String deptName) {
//		this.deptName = deptName;
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
//	public String getTypeName() {
//		return typedic.getTypeName();
//	}
//	public void setTypeName(String typeName) {
//		this.typeName = typeName;
//	}

}
