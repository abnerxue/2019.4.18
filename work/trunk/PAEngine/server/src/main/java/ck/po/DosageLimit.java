package ck.po;

import java.io.Serializable;

import lombok.Data;

//用量限制表
@Data
public class DosageLimit implements Serializable
{
	private static final long serialVersionUID = 4585182493746555456L;
	private int id;
	private String ruleName;//规则名称
	private double dosagePre;//用量1
	private double doesagePost;//用量2
	private int unitNamePre;//单位1
	private int unitNamePost;//单位2
	private int relationalOperatorPre;//关系运算符1
	private int relationalOperatorPost;//关系运算符2
	private int level;//审核等级(警告，人工审核，打回)
	private int medcineId;//药品表ID

}
