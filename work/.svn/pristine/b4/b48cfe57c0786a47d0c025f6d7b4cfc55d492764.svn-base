package ck.po;

import java.io.Serializable;

import lombok.Data;

//配伍管理和相互作用，重复用药(单个药品与类别)
@Data
public class CompatibilityInterplayMedcineCategory implements Serializable
{
	private static final long serialVersionUID = -8362993745857489833L;
	private int id;
	private int medcinePairId;//药品一ID
	private int categoryId;//类别ID
	private String reactionDescription;//描述
	private int auditLevelId;//审核等级ID
	private int type;//类型(1=配伍禁忌 ,2=相互作用,3=重复用药)
}
