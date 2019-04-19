package com.ck.springboot.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
public class PrescriptionHistorySee {
	private int id;
	private int reviewState;//复审处理状态(超时通过，自动通过，警告，单签通过，双签通过，人工打回，自动打回)
	private String prescriptionNumber;//处方号
	private String visitNumber;//就诊号
	private int patientId;//患者姓名Id
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date submmitTime;//提交时间
	private int auditPharmacist;//审核药师
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date auditTime;//审核时间
	private int auditCenter;//审方中心
	private int doctorId;//医生ID
	private int deptId;//科室id(外键)
	
	private Patient patient;
	private String patientName;
	private int patientSex;
	private AuditCenter auditcenter;
	private String auditCenterName;
	
	private UnitName unitname;
	private String unitName;
	
	private HospitalStaff hospitalstaff;
	private String auditPharmacistName;
	private String doctorName;
	private MedicalDept medicaldept;
	private String deptName;
	
	private AuditState auditstate;
	private String reviewName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReviewState() {
		return reviewState;
	}
	public void setReviewState(int reviewState) {
		this.reviewState = reviewState;
	}
	public String getPrescriptionNumber() {
		return prescriptionNumber;
	}
	public void setPrescriptionNumber(String prescriptionNumber) {
		this.prescriptionNumber = prescriptionNumber;
	}
	public String getVisitNumber() {
		return visitNumber;
	}
	public void setVisitNumber(String visitNumber) {
		this.visitNumber = visitNumber;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public Date getSubmmitTime() {
		return submmitTime;
	}
	public void setSubmmitTime(Date submmitTime) {
		this.submmitTime = submmitTime;
	}
	public int getAuditPharmacist() {
		return auditPharmacist;
	}
	public void setAuditPharmacist(int auditPharmacist) {
		this.auditPharmacist = auditPharmacist;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public int getAuditCenter() {
		return auditCenter;
	}
	public void setAuditCenter(int auditCenter) {
		this.auditCenter = auditCenter;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getPatientName() {
	try {
		return patient.getPatientName();
	} catch (Exception e) {
		return null;
	}
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getPatientSex() {
		try {
			return patient.getGender();
		} catch (Exception e) {
			return -1;
		}
		
	}
	public void setPatientSex(int patientSex) {
		this.patientSex = patientSex;
	}
	public String getAuditCenterName() {
		return auditcenter.getAuditCenterName();
	}
	public void setAuditCenterName(String auditCenterName) {
		this.auditCenterName = auditCenterName;
	}
	public String getUnitName() {
		return unitname.getUnitName();
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getAuditPharmacistName() {
		return hospitalstaff.getAuditPharmacistName();
	}
	public void setAuditPharmacistName(String auditPharmacistName) {
		this.auditPharmacistName = auditPharmacistName;
	}
	public String getDoctorName() {
		return hospitalstaff.getDoctorName();
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDeptName() {
		return medicaldept.getDeptName();
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getReviewName() {
		return auditstate.getReviewName();
	}
	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}
	
}
