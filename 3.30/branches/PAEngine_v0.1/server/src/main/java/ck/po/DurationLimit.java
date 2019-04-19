package ck.po;

import java.io.Serializable;

import lombok.Data;

//时长限制表
@Data
public class DurationLimit implements Serializable
{
	private static final long serialVersionUID = -3006452704614251639L;
	private int id;
	private int medcineId;//药品表ID
	private String ruleName;//规则名称
	private double lowerBound;//下界
	private double upperBound;//上界
	private int level;//审方等级(警告，人工审核，打回)

}
