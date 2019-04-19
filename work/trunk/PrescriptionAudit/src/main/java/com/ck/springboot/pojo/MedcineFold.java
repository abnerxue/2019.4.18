package com.ck.springboot.pojo;

import lombok.Data;


@Data
public class MedcineFold {
	private int id;
	private String name;	//通用名
//	private String type;	//类型(中药，西药，中成药)
//	private String category;//大分类
	private String subcategory;	//小分类
	private String sign;	//作为识别是药品还是分类的标志

}
