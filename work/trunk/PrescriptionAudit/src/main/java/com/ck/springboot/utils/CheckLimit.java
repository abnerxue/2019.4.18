package com.ck.springboot.utils;

import java.util.List;

import lombok.Data;
/*
 * gson映射实体
 */
@Data
public class CheckLimit {
	private int ruleId;
	private int medicineId;
	private String level;
	private String ruleName;
	private int type;
	private List<CheckRule> checkRules;
}
