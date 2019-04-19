package com.ck.springboot.pojo.pojomore;

import java.io.Serializable;

import lombok.Data;

//药品表
@Data
public class MedcineMore implements Serializable
{
	private static final long serialVersionUID = 3828444510405423390L;
	private int id;
	private String name; // 通用名
	private int type; // 类型(中药，西药，中成药)
	private int category;// 大分类j
	private int subcategory; // 小分类
	private int dosageForm; // 剂型
	private int standardSource; // 标准来源
	private int doseRecommendPerTime; // 每次推荐量
	private int doseRecommendPerDay; // 每日推荐量
	private int doseMaxPerTime; // 每次极量	
	private int doseMaxPerDay; // 每日极量
	private int frequency; // 给药频率
	private double totalDose; // 疗程总剂量
	private int maxDuration; // 最长给药持续时间
	private int maxMultidaysDose; // 最大超多日用量
	private String routeOfMedication; // 给药途径
	private boolean skinTest;// 皮试要求
	private String mentalStupefacientToxic;// 精麻毒药品判定
	private boolean needAudit; // 是否加入审方
	private String specification; // 规格
	private int dailyDose; // 给药次数
	private boolean isAntibiotic; // 是否为抗生素
	private boolean platformStandard; // 是否平台标准

}