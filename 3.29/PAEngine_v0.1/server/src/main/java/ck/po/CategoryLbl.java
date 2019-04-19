package ck.po;

import java.io.Serializable;

import lombok.Data;

//配伍管理和相互作用，重复用药(标签与类别)
@Data
public class CategoryLbl implements Serializable
{
	private static final long serialVersionUID = 2118291111532897343L;
	private int categoryId;//类ID
	private int lblId;//标签ID
	private String reactionDescription;//反应
	private int auditLevelId;//审核等级ID
	private int type;//类型(1=配伍禁忌 ,2=相互作用)
	
}
