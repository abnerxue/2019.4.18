package com.ck.springboot.pojo;

public class MedcineHos {
	private int id;
	private String name;	//通用名
	private int type;	//类型(中药，西药，中成药)
	private int category;//大分类
	private int subcategory;	//小分类
	private int standardSource;	//标准来源
	private int doseRecommendPerTime;	//每次推荐量
	private int doseRecommendPerDay;	//每日推荐量
	private int doseMaxPerTime;	//每次极量
	private int doseMaxPerDay;	//每日极量
	private int frequency;	//给药频率
	private int totalDose;	//疗程总剂量
	private int maxDuration;	//最长给药持续时间
	private int maxMultidaysDose;	//最大超多日用量
	private String routeOfMedication;	//给药途径
	private int skinTest;//皮试要求
	private String mentalStupefacientToxic;//精麻毒药品判定
	private int needAudit;	//是否加入审方
	

	private Type typedic;
	private String typeName;
	
	private Type2 typedic2;
	private String mentalStupefacientToxicName;
	
	private Classify1 classify1;
	private String categoryName;
	
	private Classify2 classify2;
	private String subcategoryName;
	
	private UnitName unit;
	private String unitName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(int subcategory) {
		this.subcategory = subcategory;
	}
	public int getStandardSource() {
		return standardSource;
	}
	public void setStandardSource(int standardSource) {
		this.standardSource = standardSource;
	}
	public int getDoseRecommendPerTime() {
		return doseRecommendPerTime;
	}
	public void setDoseRecommendPerTime(int doseRecommendPerTime) {
		this.doseRecommendPerTime = doseRecommendPerTime;
	}
	public int getDoseRecommendPerDay() {
		return doseRecommendPerDay;
	}
	public void setDoseRecommendPerDay(int doseRecommendPerDay) {
		this.doseRecommendPerDay = doseRecommendPerDay;
	}
	public int getDoseMaxPerTime() {
		return doseMaxPerTime;
	}
	public void setDoseMaxPerTime(int doseMaxPerTime) {
		this.doseMaxPerTime = doseMaxPerTime;
	}
	public int getDoseMaxPerDay() {
		return doseMaxPerDay;
	}
	public void setDoseMaxPerDay(int doseMaxPerDay) {
		this.doseMaxPerDay = doseMaxPerDay;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public int getTotalDose() {
		return totalDose;
	}
	public void setTotalDose(int totalDose) {
		this.totalDose = totalDose;
	}
	public int getMaxDuration() {
		return maxDuration;
	}
	public void setMaxDuration(int maxDuration) {
		this.maxDuration = maxDuration;
	}
	public int getMaxMultidaysDose() {
		return maxMultidaysDose;
	}
	public void setMaxMultidaysDose(int maxMultidaysDose) {
		this.maxMultidaysDose = maxMultidaysDose;
	}
	public String getRouteOfMedication() {
		return routeOfMedication;
	}
	public void setRouteOfMedication(String routeOfMedication) {
		this.routeOfMedication = routeOfMedication;
	}
	public int getSkinTest() {
		return skinTest;
	}
	public void setSkinTest(int skinTest) {
		this.skinTest = skinTest;
	}
	public String getMentalStupefacientToxic() {
		return mentalStupefacientToxic;
	}
	public void setMentalStupefacientToxic(String mentalStupefacientToxic) {
		this.mentalStupefacientToxic = mentalStupefacientToxic;
	}
	public int getNeedAudit() {
		return needAudit;
	}
	public void setNeedAudit(int needAudit) {
		this.needAudit = needAudit;
	}

	public String getTypeName() {
		return typedic.getTypeName();
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCategoryName() {
		return classify1.getCategoryName();
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubcategoryName() {
		return classify2.getSubcategoryName();
	}
	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}
	public String getMentalStupefacientToxicName() {
		return typedic2.getMentalStupefacientToxicName();
	}
	public void setMentalStupefacientToxicName(String mentalStupefacientToxicName) {
		this.mentalStupefacientToxicName = mentalStupefacientToxicName;
	}
	public String getUnitName() {
		return unit.getUnitName();
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	
	
}
