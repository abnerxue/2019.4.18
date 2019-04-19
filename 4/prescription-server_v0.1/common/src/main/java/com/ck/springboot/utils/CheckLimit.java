package com.ck.springboot.utils;

import lombok.Data;

import java.util.List;

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
