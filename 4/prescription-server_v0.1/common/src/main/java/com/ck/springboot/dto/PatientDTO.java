package com.ck.springboot.dto;

import java.io.Serializable;
import java.util.HashMap;

import lombok.Data;

@Data
public class PatientDTO implements Serializable {
	private static final long serialVersionUID = 1584857944916616032L;
	private String name; // 姓名
	private Boolean gender; // 性别
	private int age; // 年龄
	private String address;// 住址
	private String allergyHistory;// 过敏史
	private float weight;// 体重
	private String birthday; // 出生日期
	private String socialSecurityNumber; // 社保号
	private String idNumber; // 身份证号码
	private float height; // 身高
	private String specialGroup; // 特殊人群
	// 新加属性
	private boolean isLactation;// 是否哺乳（哺乳为true,未哺乳为false）
	private boolean isPregnancy;// 是否怀孕(怀孕为true,未怀孕为false)
	private int gestationalWeek;// 孕周(怀孕第几周)
	private Integer birthDays;//出生天数
	private Integer birthMonths;//出生月数
	private HashMap<String, Integer> inspectionItems;//检查项目 k为项目名 v为结果(只需要跟肝功能，肾功能相关的项目)

}