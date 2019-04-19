package com.ck.springboot.pojo;

public class CheckLimit {
    private Integer id;

    private String ruleName;

    private Double lowerbound;

    private Double upperbound;

    private Integer checkItemsId;

    private Integer liverKidneyDicId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Double getLowerbound() {
        return lowerbound;
    }

    public void setLowerbound(Double lowerbound) {
        this.lowerbound = lowerbound;
    }

    public Double getUpperbound() {
        return upperbound;
    }

    public void setUpperbound(Double upperbound) {
        this.upperbound = upperbound;
    }

    public Integer getCheckItemsId() {
        return checkItemsId;
    }

    public void setCheckItemsId(Integer checkItemsId) {
        this.checkItemsId = checkItemsId;
    }

    public Integer getLiverKidneyDicId() {
        return liverKidneyDicId;
    }

    public void setLiverKidneyDicId(Integer liverKidneyDicId) {
        this.liverKidneyDicId = liverKidneyDicId;
    }
}