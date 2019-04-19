package com.ck.springboot.pojo.report;

import java.util.List;
import com.ck.springboot.pojo.HospitalStaff;
import com.ck.springboot.pojo.MedicalDept;
import com.ck.springboot.pojo.Patient;
import com.ck.springboot.pojo.ProblemCode;

public class PrescriptionHistoryReportDetail {
	private int id;
	private String prescriptionNumber;//处方号
	private int patientId;//患者姓名Id
	private int auditPharmacist;//审核药师
	private int doctorId;//医生ID
	private int deptId;//科室id(外键)
	
	private Patient patient;
	private String patientName;
	private int patientSex;

	private HospitalStaff hospitalstaff;
	private String auditPharmacistName;
	private String doctorName;
	
	private MedicalDept medicaldept;
	private String deptName;

	private String auditResult;
	private String problemName;
//	private List<ProblemCode> problemcode;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPrescriptionNumber() {
		return prescriptionNumber;
	}


	public void setPrescriptionNumber(String prescriptionNumber) {
		this.prescriptionNumber = prescriptionNumber;
	}


	public int getPatientId() {
		return patientId;
	}


	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}


	public int getAuditPharmacist() {
		return auditPharmacist;
	}


	public void setAuditPharmacist(int auditPharmacist) {
		this.auditPharmacist = auditPharmacist;
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
		return patient.getPatientName();
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public int getPatientSex() {
		return patient.getGender();
	}


	public void setPatientSex(int patientSex) {
		this.patientSex = patientSex;
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


	public String getAuditResult() {
		return auditResult;
	}


	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}


	public String getProblemName() {
		return problemName;
	}


	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

//
//	public List<ProblemCode> getProblemcode() {
//		return problemcode;
//	}
//
//
//	public void setProblemcode(List<ProblemCode> problemcode) {
//		this.problemcode = problemcode;
//	}
//	
	
}
