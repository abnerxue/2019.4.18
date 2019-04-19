package com.ck.springboot.pojo;


import lombok.Data;


@Data
public class CrowdRestrictionMedcine {
	
	private Integer id;
	
    private Integer tCrowdId;

    private Integer medcineId;

    private Integer action;
    
}