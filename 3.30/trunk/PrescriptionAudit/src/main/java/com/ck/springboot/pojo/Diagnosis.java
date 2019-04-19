package com.ck.springboot.pojo;

import lombok.Data;

@Data
public class Diagnosis {
    private Integer id;

    private String diagnosisName;

    private String icd10;

}