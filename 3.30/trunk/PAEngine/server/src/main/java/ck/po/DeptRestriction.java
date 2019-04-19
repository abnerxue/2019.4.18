package ck.po;

import java.io.Serializable;

import lombok.Data;

//医院科室药品限制关系表
@Data
public class DeptRestriction implements Serializable
{
	private static final long serialVersionUID = 1006424853077762257L;
	private int id;
	private int medcineId;
	private int deptId;
	private int action;

}