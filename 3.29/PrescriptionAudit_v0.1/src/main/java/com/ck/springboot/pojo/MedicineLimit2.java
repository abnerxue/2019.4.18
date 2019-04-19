package com.ck.springboot.pojo;

import java.util.List;

import lombok.Data;

@Data
public class MedicineLimit2 {
	private int ruleId;
	private int medicineId;
	private String level;
	private String ruleName;
	private int type;
	private List<CheckRules> checkRules;
}
