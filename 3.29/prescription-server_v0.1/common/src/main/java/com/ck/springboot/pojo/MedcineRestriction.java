package com.ck.springboot.pojo;

import lombok.Data;

@Data
public class MedcineRestriction {
    private Integer id;
    
    private Integer diagnosisId;

    private Integer medcineId;

    private Integer action;

}