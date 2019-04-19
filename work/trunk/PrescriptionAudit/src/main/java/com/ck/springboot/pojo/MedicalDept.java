package com.ck.springboot.pojo;

import lombok.Data;

@Data
public class MedicalDept {
    private Integer id;

    private String deptName;

    private Integer hospitalId;
}