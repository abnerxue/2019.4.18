package com.ck.springboot.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProblemCode {
	private int id;
//	private String action;
	
	private String prescriptionNumber;//处方号
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date submitTime;//提交时间
	private int medicalUnit;//就医单位名称
	private String problemCode;//问题代码
	private String problemName;//问题描述
	private String auditResult;//审核结果
	private String pharmacistRemark;//药师备注
	private int problemtype;//类型(1.审方系统， 2.区域审方平台)

}
