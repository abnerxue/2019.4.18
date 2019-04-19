package com.ck.springboot.pojo;

import lombok.Data;

@Data
public class LblMedcine {
	private int id;
	private int mecineId;
	private int labelId;
	private String reactionDescription;//描述
	private String action;//审核等级
}
