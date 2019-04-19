package com.ck.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PrescriptionHistoryAndPatient {
	private int id;
	private int patientId;
	private int isProblem = 0;//0没问题 1有问题
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date auditTime;//初审时间
	
	private String socialSecurityNumber;//社保号
	private String idNumber;//身份证号
	
	private List<Diagnosis> diagnosis;
	private List<PrescriptionMedcine> prescriptionMedcine;
//	private String name;//药物名
	
	
}
