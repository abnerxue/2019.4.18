package com.ck.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Patient {
    private Integer id;

    private String patientName;

    private Integer gender;

    private Integer age;

    private String address;

    private String allergyHistory;

    private Double weight;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthday;
	
	private String socialSecurityNumber;//社保号
	private String idNumber;//身份证号
	private Double height;//身高
	private String specialGroup;//特殊人群
	
}