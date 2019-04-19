package com.ck.springboot.pojo;

import java.math.BigDecimal;

public class DosageLimit {
    private Integer id;

    private String ruleName;

    private BigDecimal dosagePre;

    private BigDecimal doesagePost;

    private String unitNamePre;

    private String unitNamePost;

    private String relationalOperatorPre;

    private String relationalOperatorPost;

    private Integer level;

    private Integer medcineId;

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

    public BigDecimal getDosagePre() {
        return dosagePre;
    }

    public void setDosagePre(BigDecimal dosagePre) {
        this.dosagePre = dosagePre;
    }

    public BigDecimal getDoesagePost() {
        return doesagePost;
    }

    public void setDoesagePost(BigDecimal doesagePost) {
        this.doesagePost = doesagePost;
    }

    public String getUnitNamePre() {
        return unitNamePre;
    }

    public void setUnitNamePre(String unitNamePre) {
        this.unitNamePre = unitNamePre == null ? null : unitNamePre.trim();
    }

    public String getUnitNamePost() {
        return unitNamePost;
    }

    public void setUnitNamePost(String unitNamePost) {
        this.unitNamePost = unitNamePost == null ? null : unitNamePost.trim();
    }

    public String getRelationalOperatorPre() {
        return relationalOperatorPre;
    }

    public void setRelationalOperatorPre(String relationalOperatorPre) {
        this.relationalOperatorPre = relationalOperatorPre == null ? null : relationalOperatorPre.trim();
    }

    public String getRelationalOperatorPost() {
        return relationalOperatorPost;
    }

    public void setRelationalOperatorPost(String relationalOperatorPost) {
        this.relationalOperatorPost = relationalOperatorPost == null ? null : relationalOperatorPost.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMedcineId() {
        return medcineId;
    }

    public void setMedcineId(Integer medcineId) {
        this.medcineId = medcineId;
    }
}