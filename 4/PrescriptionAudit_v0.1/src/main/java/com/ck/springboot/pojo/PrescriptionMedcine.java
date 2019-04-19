package com.ck.springboot.pojo;

import lombok.Data;

@Data
public class PrescriptionMedcine {
    private int prescriptionHistoryId;

    private int medcineId;

    private String total;//总量
    
    private String specification;//规格
    
    private String medcineName;//药品名称
    
    private String route;//给药途径
    
    private String usage;//用法
    
    private String EachDose;//每次剂量
    
    private String prescriptionNumber;//处方号
    private String frequency;//频率
    private int auditCenterId;//审方中心id
}