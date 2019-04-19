package com.ck.springboot.pojo;

import lombok.Data;

/**
 * 检查限用
 * @author Administrator
 *
 */
@Data
public class MedicineLimit {
	private int id;
	private int medicineId;
	private String ruleName;//规则名称
	private double lowerbound;
	private double upperbound;
	private String level;//审核等级；
	private int type;//1肝功能，2肾功能
	
	private int checkItemId;//项目表id
	private String name;//审核项目名称
	private String unitName;//单位名称
}
