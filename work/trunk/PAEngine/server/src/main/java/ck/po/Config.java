package ck.po;

import java.io.Serializable;

import lombok.Data;

//系统配置
@Data
public class Config implements Serializable
{
	private static final long serialVersionUID = 4605862249459637820L;
	private int id;
	private String name;
	private String values;

}