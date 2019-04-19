package com.ck.springboot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class MedicineLimit2 {
	private int ruleId;
	private int medicineId;
	private String level;
	private String ruleName;
	private int type;
	private List<CheckRules> checkRules;
}
