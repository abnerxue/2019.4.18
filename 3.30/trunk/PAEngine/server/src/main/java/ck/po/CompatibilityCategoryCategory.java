package ck.po;

import java.io.Serializable;

import lombok.Data;

//配伍管理和相互作用，重复用药(类别与类别)
@Data
public class CompatibilityCategoryCategory implements Serializable
{
	private static final long serialVersionUID = -8248630651245578582L;
	private int categoryId;//类别ID
	private int categoryPairId;//配伍禁忌对应类ID
	private String reactionDescription;//描述
	private int auditLevelId;//审核等级ID
	private int type;//类型(1=配伍禁忌 ,2=相互作用,3=重复用药)
}
