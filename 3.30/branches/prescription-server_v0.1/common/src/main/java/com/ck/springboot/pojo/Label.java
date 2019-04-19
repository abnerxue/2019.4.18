package com.ck.springboot.pojo;

import lombok.Data;

@Data
public class Label {
	private int id;
	private String name;//标签名
	
	private int medicineId;//药品id
	private String medicineName;//药物吗
}
