package com.ck.springboot.pojo;
/**
 * 检查值设置实体
 * @author Administrator
 *
 */
public class ZyyCheckLimt {
	private int id;
	private String ruleName;
	private double lowerbound;
	private double upperbound;
	private int checkItemsId;
	
	
	private int liverkidney;
	
	private String name;//检查项目名称
	private String unitName;//单位名称
	
//	private String description;//肝肾功能描述
	
	
	
	
	public String getName() {
		return name;
	}
	public int getLiverkidney() {
		return liverkidney;
	}
	public void setLiverkidney(int liverkidney) {
		this.liverkidney = liverkidney;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public double getLowerbound() {
		return lowerbound;
	}
	public void setLowerbound(double lowerbound) {
		this.lowerbound = lowerbound;
	}
	public double getUpperbound() {
		return upperbound;
	}
	public void setUpperbound(double upperbound) {
		this.upperbound = upperbound;
	}
	public int getCheckItemsId() {
		return checkItemsId;
	}
	public void setCheckItemsId(int checkItemsId) {
		this.checkItemsId = checkItemsId;
	}

	
	
}