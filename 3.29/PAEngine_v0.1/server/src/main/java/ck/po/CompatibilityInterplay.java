package ck.po;

import java.io.Serializable;

import lombok.Data;

//配伍管理和相互作用，重复用药表
@Data
public class CompatibilityInterplay implements Serializable
{
	private static final long serialVersionUID = -8631585621010365979L;
	private int id;
	private int medcinePairId;//药品一ID
	private String reactionDescription;//描述
	private int medcineId;//药品二ID
	private int auditLevelId;//审核等级ID
	private int type;//类型(1=配伍禁忌 ,2=相互作用,3=重复用药)
}
