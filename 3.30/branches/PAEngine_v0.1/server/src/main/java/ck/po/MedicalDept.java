package ck.po;

import java.io.Serializable;

import lombok.Data;

//医院部门表
@Data
public class MedicalDept implements Serializable
{
	private static final long serialVersionUID = 5663196156669300559L;
	private int id;
	private String name;//医院部门名称
	private int hospitalId;//医院ID
}