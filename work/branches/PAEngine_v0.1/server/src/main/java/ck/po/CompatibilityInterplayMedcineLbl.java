package ck.po;

import java.io.Serializable;

import lombok.Data;

//配伍管理和相互作用，重复用药(单个药品与标签)
@Data
public class CompatibilityInterplayMedcineLbl implements Serializable
{
	private static final long serialVersionUID = 8923292442454115283L;
	private int id;
	private int lblId;//标签ID
	private int medcineId;//药品ID
	private String reactionDescription;//反应
	private int auditLevelId;//审核等级ID
	private int type;//类型(1=配伍禁忌 ,2=相互作用,3=重复用药)
}
