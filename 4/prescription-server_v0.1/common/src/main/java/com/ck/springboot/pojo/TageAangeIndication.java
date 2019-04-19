package com.ck.springboot.pojo;

import lombok.Data;
/**
 * 年龄适用症
 * @author Administrator
 *
 */
@Data
public class TageAangeIndication {
	private int id;
	private int medcineId;
	private String medicineName;
	private int diagnosisId;
	
	private int under_3_month;//3个月审核状态
	private int under_6_month;//6个月审核状态
	private int under_2_year;
	private int under_12_year;
	private int under_16_year;
	private int under_18_year;
	private int above_60_year;
	private int above_70_year;
	
}
