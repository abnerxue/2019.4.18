package com.ck.springboot.pojo;

import lombok.Data;

@Data
public class AuditRules {
	private int id;
	
	private String code;
	
	private String problemName;
	
	private int action;
	
	private int enable;
}
