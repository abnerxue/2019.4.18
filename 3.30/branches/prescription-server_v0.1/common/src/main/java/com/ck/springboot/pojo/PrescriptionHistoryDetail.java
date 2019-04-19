package com.ck.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PrescriptionHistoryDetail {
	private int id;
	private String state;//初审状态(超时通过，自动通过，警告，单签通过，双签通过，人工打回，自动打回)
	private String reviewState;//复审处理状态(超时通过，自动通过，警告，单签通过，双签通过，人工打回，自动打回)
	private int prescriptionNumber;//处方号
	private String prescriptionContent;//处方内容
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
	private int diagnosisId;//诊断ID
	
	private MedicalDept medicaldept;

	private Patient patient;

	private List<Diagnosis> diagnosis;
	
	private List<PrescriptionMedcine> premedcine;

	private List<ProblemCode> problemcode;
	
	private List<ProblemCode> problemcodehos;
	
	private HospitalStaff hospitalstaff;
	
	private UnitName unitname;
	
	private AuditCenterSee auditcentersee;
	


}
