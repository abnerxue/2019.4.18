package com.ck.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties
@Data
public class UserLogin {
	private int id;
	private String name;
	private String password;
	private Integer deptid;
	private Integer roleid;

}
