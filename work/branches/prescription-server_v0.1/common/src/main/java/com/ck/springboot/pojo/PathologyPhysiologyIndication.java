package com.ck.springboot.pojo;

public class PathologyPhysiologyIndication {
    private Integer id;

    private Integer diagnosisId;

    private Integer medcineId;

    private Integer lactation;

    private Integer liverFunction;

    private Integer liverFunctionUparrow;

    private Integer kidneyFunction;

    private Integer kidneyFunctionUparrow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Integer diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Integer getMedcineId() {
        return medcineId;
    }

    public void setMedcineId(Integer medcineId) {
        this.medcineId = medcineId;
    }

    public Integer getLactation() {
        return lactation;
    }

    public void setLactation(Integer lactation) {
        this.lactation = lactation;
    }

    public Integer getLiverFunction() {
        return liverFunction;
    }

    public void setLiverFunction(Integer liverFunction) {
        this.liverFunction = liverFunction;
    }

    public Integer getLiverFunctionUparrow() {
        return liverFunctionUparrow;
    }

    public void setLiverFunctionUparrow(Integer liverFunctionUparrow) {
        this.liverFunctionUparrow = liverFunctionUparrow;
    }

    public Integer getKidneyFunction() {
        return kidneyFunction;
    }

    public void setKidneyFunction(Integer kidneyFunction) {
        this.kidneyFunction = kidneyFunction;
    }

    public Integer getKidneyFunctionUparrow() {
        return kidneyFunctionUparrow;
    }

    public void setKidneyFunctionUparrow(Integer kidneyFunctionUparrow) {
        this.kidneyFunctionUparrow = kidneyFunctionUparrow;
    }
}