package ck.po;

import java.io.Serializable;

import lombok.Data;

//检查项目字典表
@Data
public class CheckItems implements Serializable
{
	private static final long serialVersionUID = 8874639854188459122L;
	private int id;
	private String name;	//检验名称
	private String unitName;	//单位
}
