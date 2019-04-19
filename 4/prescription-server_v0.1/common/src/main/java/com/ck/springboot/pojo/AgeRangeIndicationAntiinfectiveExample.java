package com.ck.springboot.pojo;

import java.util.ArrayList;
import java.util.List;

public class AgeRangeIndicationAntiinfectiveExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AgeRangeIndicationAntiinfectiveExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMedcineIdIsNull() {
            addCriterion("medcine_id is null");
            return (Criteria) this;
        }

        public Criteria andMedcineIdIsNotNull() {
            addCriterion("medcine_id is not null");
            return (Criteria) this;
        }

        public Criteria andMedcineIdEqualTo(Integer value) {
            addCriterion("medcine_id =", value, "medcineId");
            return (Criteria) this;
        }

        public Criteria andMedcineIdNotEqualTo(Integer value) {
            addCriterion("medcine_id <>", value, "medcineId");
            return (Criteria) this;
        }

        public Criteria andMedcineIdGreaterThan(Integer value) {
            addCriterion("medcine_id >", value, "medcineId");
            return (Criteria) this;
        }

        public Criteria andMedcineIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("medcine_id >=", value, "medcineId");
            return (Criteria) this;
        }

        public Criteria andMedcineIdLessThan(Integer value) {
            addCriterion("medcine_id <", value, "medcineId");
            return (Criteria) this;
        }

        public Criteria andMedcineIdLessThanOrEqualTo(Integer value) {
            addCriterion("medcine_id <=", value, "medcineId");
            return (Criteria) this;
        }

        public Criteria andMedcineIdIn(List<Integer> values) {
            addCriterion("medcine_id in", values, "medcineId");
            return (Criteria) this;
        }

        public Criteria andMedcineIdNotIn(List<Integer> values) {
            addCriterion("medcine_id not in", values, "medcineId");
            return (Criteria) this;
        }

        public Criteria andMedcineIdBetween(Integer value1, Integer value2) {
            addCriterion("medcine_id between", value1, value2, "medcineId");
            return (Criteria) this;
        }

        public Criteria andMedcineIdNotBetween(Integer value1, Integer value2) {
            addCriterion("medcine_id not between", value1, value2, "medcineId");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdIsNull() {
            addCriterion("diagnosis_id is null");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdIsNotNull() {
            addCriterion("diagnosis_id is not null");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdEqualTo(Integer value) {
            addCriterion("diagnosis_id =", value, "diagnosisId");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdNotEqualTo(Integer value) {
            addCriterion("diagnosis_id <>", value, "diagnosisId");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdGreaterThan(Integer value) {
            addCriterion("diagnosis_id >", value, "diagnosisId");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("diagnosis_id >=", value, "diagnosisId");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdLessThan(Integer value) {
            addCriterion("diagnosis_id <", value, "diagnosisId");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdLessThanOrEqualTo(Integer value) {
            addCriterion("diagnosis_id <=", value, "diagnosisId");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdIn(List<Integer> values) {
            addCriterion("diagnosis_id in", values, "diagnosisId");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdNotIn(List<Integer> values) {
            addCriterion("diagnosis_id not in", values, "diagnosisId");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdBetween(Integer value1, Integer value2) {
            addCriterion("diagnosis_id between", value1, value2, "diagnosisId");
            return (Criteria) this;
        }

        public Criteria andDiagnosisIdNotBetween(Integer value1, Integer value2) {
            addCriterion("diagnosis_id not between", value1, value2, "diagnosisId");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthIsNull() {
            addCriterion("under_3_month is null");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthIsNotNull() {
            addCriterion("under_3_month is not null");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthEqualTo(Integer value) {
            addCriterion("under_3_month =", value, "under3Month");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthNotEqualTo(Integer value) {
            addCriterion("under_3_month <>", value, "under3Month");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthGreaterThan(Integer value) {
            addCriterion("under_3_month >", value, "under3Month");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("under_3_month >=", value, "under3Month");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthLessThan(Integer value) {
            addCriterion("under_3_month <", value, "under3Month");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthLessThanOrEqualTo(Integer value) {
            addCriterion("under_3_month <=", value, "under3Month");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthIn(List<Integer> values) {
            addCriterion("under_3_month in", values, "under3Month");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthNotIn(List<Integer> values) {
            addCriterion("under_3_month not in", values, "under3Month");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthBetween(Integer value1, Integer value2) {
            addCriterion("under_3_month between", value1, value2, "under3Month");
            return (Criteria) this;
        }

        public Criteria andUnder3MonthNotBetween(Integer value1, Integer value2) {
            addCriterion("under_3_month not between", value1, value2, "under3Month");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthIsNull() {
            addCriterion("under_6_month is null");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthIsNotNull() {
            addCriterion("under_6_month is not null");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthEqualTo(Integer value) {
            addCriterion("under_6_month =", value, "under6Month");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthNotEqualTo(Integer value) {
            addCriterion("under_6_month <>", value, "under6Month");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthGreaterThan(Integer value) {
            addCriterion("under_6_month >", value, "under6Month");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("under_6_month >=", value, "under6Month");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthLessThan(Integer value) {
            addCriterion("under_6_month <", value, "under6Month");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthLessThanOrEqualTo(Integer value) {
            addCriterion("under_6_month <=", value, "under6Month");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthIn(List<Integer> values) {
            addCriterion("under_6_month in", values, "under6Month");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthNotIn(List<Integer> values) {
            addCriterion("under_6_month not in", values, "under6Month");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthBetween(Integer value1, Integer value2) {
            addCriterion("under_6_month between", value1, value2, "under6Month");
            return (Criteria) this;
        }

        public Criteria andUnder6MonthNotBetween(Integer value1, Integer value2) {
            addCriterion("under_6_month not between", value1, value2, "under6Month");
            return (Criteria) this;
        }

        public Criteria andUnder2YearIsNull() {
            addCriterion("under_2_year is null");
            return (Criteria) this;
        }

        public Criteria andUnder2YearIsNotNull() {
            addCriterion("under_2_year is not null");
            return (Criteria) this;
        }

        public Criteria andUnder2YearEqualTo(Integer value) {
            addCriterion("under_2_year =", value, "under2Year");
            return (Criteria) this;
        }

        public Criteria andUnder2YearNotEqualTo(Integer value) {
            addCriterion("under_2_year <>", value, "under2Year");
            return (Criteria) this;
        }

        public Criteria andUnder2YearGreaterThan(Integer value) {
            addCriterion("under_2_year >", value, "under2Year");
            return (Criteria) this;
        }

        public Criteria andUnder2YearGreaterThanOrEqualTo(Integer value) {
            addCriterion("under_2_year >=", value, "under2Year");
            return (Criteria) this;
        }

        public Criteria andUnder2YearLessThan(Integer value) {
            addCriterion("under_2_year <", value, "under2Year");
            return (Criteria) this;
        }

        public Criteria andUnder2YearLessThanOrEqualTo(Integer value) {
            addCriterion("under_2_year <=", value, "under2Year");
            return (Criteria) this;
        }

        public Criteria andUnder2YearIn(List<Integer> values) {
            addCriterion("under_2_year in", values, "under2Year");
            return (Criteria) this;
        }

        public Criteria andUnder2YearNotIn(List<Integer> values) {
            addCriterion("under_2_year not in", values, "under2Year");
            return (Criteria) this;
        }

        public Criteria andUnder2YearBetween(Integer value1, Integer value2) {
            addCriterion("under_2_year between", value1, value2, "under2Year");
            return (Criteria) this;
        }

        public Criteria andUnder2YearNotBetween(Integer value1, Integer value2) {
            addCriterion("under_2_year not between", value1, value2, "under2Year");
            return (Criteria) this;
        }

        public Criteria andUnder12YearIsNull() {
            addCriterion("under_12_year is null");
            return (Criteria) this;
        }

        public Criteria andUnder12YearIsNotNull() {
            addCriterion("under_12_year is not null");
            return (Criteria) this;
        }

        public Criteria andUnder12YearEqualTo(Integer value) {
            addCriterion("under_12_year =", value, "under12Year");
            return (Criteria) this;
        }

        public Criteria andUnder12YearNotEqualTo(Integer value) {
            addCriterion("under_12_year <>", value, "under12Year");
            return (Criteria) this;
        }

        public Criteria andUnder12YearGreaterThan(Integer value) {
            addCriterion("under_12_year >", value, "under12Year");
            return (Criteria) this;
        }

        public Criteria andUnder12YearGreaterThanOrEqualTo(Integer value) {
            addCriterion("under_12_year >=", value, "under12Year");
            return (Criteria) this;
        }

        public Criteria andUnder12YearLessThan(Integer value) {
            addCriterion("under_12_year <", value, "under12Year");
            return (Criteria) this;
        }

        public Criteria andUnder12YearLessThanOrEqualTo(Integer value) {
            addCriterion("under_12_year <=", value, "under12Year");
            return (Criteria) this;
        }

        public Criteria andUnder12YearIn(List<Integer> values) {
            addCriterion("under_12_year in", values, "under12Year");
            return (Criteria) this;
        }

        public Criteria andUnder12YearNotIn(List<Integer> values) {
            addCriterion("under_12_year not in", values, "under12Year");
            return (Criteria) this;
        }

        public Criteria andUnder12YearBetween(Integer value1, Integer value2) {
            addCriterion("under_12_year between", value1, value2, "under12Year");
            return (Criteria) this;
        }

        public Criteria andUnder12YearNotBetween(Integer value1, Integer value2) {
            addCriterion("under_12_year not between", value1, value2, "under12Year");
            return (Criteria) this;
        }

        public Criteria andUnder16YearIsNull() {
            addCriterion("under_16_year is null");
            return (Criteria) this;
        }

        public Criteria andUnder16YearIsNotNull() {
            addCriterion("under_16_year is not null");
            return (Criteria) this;
        }

        public Criteria andUnder16YearEqualTo(Integer value) {
            addCriterion("under_16_year =", value, "under16Year");
            return (Criteria) this;
        }

        public Criteria andUnder16YearNotEqualTo(Integer value) {
            addCriterion("under_16_year <>", value, "under16Year");
            return (Criteria) this;
        }

        public Criteria andUnder16YearGreaterThan(Integer value) {
            addCriterion("under_16_year >", value, "under16Year");
            return (Criteria) this;
        }

        public Criteria andUnder16YearGreaterThanOrEqualTo(Integer value) {
            addCriterion("under_16_year >=", value, "under16Year");
            return (Criteria) this;
        }

        public Criteria andUnder16YearLessThan(Integer value) {
            addCriterion("under_16_year <", value, "under16Year");
            return (Criteria) this;
        }

        public Criteria andUnder16YearLessThanOrEqualTo(Integer value) {
            addCriterion("under_16_year <=", value, "under16Year");
            return (Criteria) this;
        }

        public Criteria andUnder16YearIn(List<Integer> values) {
            addCriterion("under_16_year in", values, "under16Year");
            return (Criteria) this;
        }

        public Criteria andUnder16YearNotIn(List<Integer> values) {
            addCriterion("under_16_year not in", values, "under16Year");
            return (Criteria) this;
        }

        public Criteria andUnder16YearBetween(Integer value1, Integer value2) {
            addCriterion("under_16_year between", value1, value2, "under16Year");
            return (Criteria) this;
        }

        public Criteria andUnder16YearNotBetween(Integer value1, Integer value2) {
            addCriterion("under_16_year not between", value1, value2, "under16Year");
            return (Criteria) this;
        }

        public Criteria andUnder18YearIsNull() {
            addCriterion("under_18_year is null");
            return (Criteria) this;
        }

        public Criteria andUnder18YearIsNotNull() {
            addCriterion("under_18_year is not null");
            return (Criteria) this;
        }

        public Criteria andUnder18YearEqualTo(Integer value) {
            addCriterion("under_18_year =", value, "under18Year");
            return (Criteria) this;
        }

        public Criteria andUnder18YearNotEqualTo(Integer value) {
            addCriterion("under_18_year <>", value, "under18Year");
            return (Criteria) this;
        }

        public Criteria andUnder18YearGreaterThan(Integer value) {
            addCriterion("under_18_year >", value, "under18Year");
            return (Criteria) this;
        }

        public Criteria andUnder18YearGreaterThanOrEqualTo(Integer value) {
            addCriterion("under_18_year >=", value, "under18Year");
            return (Criteria) this;
        }

        public Criteria andUnder18YearLessThan(Integer value) {
            addCriterion("under_18_year <", value, "under18Year");
            return (Criteria) this;
        }

        public Criteria andUnder18YearLessThanOrEqualTo(Integer value) {
            addCriterion("under_18_year <=", value, "under18Year");
            return (Criteria) this;
        }

        public Criteria andUnder18YearIn(List<Integer> values) {
            addCriterion("under_18_year in", values, "under18Year");
            return (Criteria) this;
        }

        public Criteria andUnder18YearNotIn(List<Integer> values) {
            addCriterion("under_18_year not in", values, "under18Year");
            return (Criteria) this;
        }

        public Criteria andUnder18YearBetween(Integer value1, Integer value2) {
            addCriterion("under_18_year between", value1, value2, "under18Year");
            return (Criteria) this;
        }

        public Criteria andUnder18YearNotBetween(Integer value1, Integer value2) {
            addCriterion("under_18_year not between", value1, value2, "under18Year");
            return (Criteria) this;
        }

        public Criteria andAbove60YearIsNull() {
            addCriterion("above_60_year is null");
            return (Criteria) this;
        }

        public Criteria andAbove60YearIsNotNull() {
            addCriterion("above_60_year is not null");
            return (Criteria) this;
        }

        public Criteria andAbove60YearEqualTo(Integer value) {
            addCriterion("above_60_year =", value, "above60Year");
            return (Criteria) this;
        }

        public Criteria andAbove60YearNotEqualTo(Integer value) {
            addCriterion("above_60_year <>", value, "above60Year");
            return (Criteria) this;
        }

        public Criteria andAbove60YearGreaterThan(Integer value) {
            addCriterion("above_60_year >", value, "above60Year");
            return (Criteria) this;
        }

        public Criteria andAbove60YearGreaterThanOrEqualTo(Integer value) {
            addCriterion("above_60_year >=", value, "above60Year");
            return (Criteria) this;
        }

        public Criteria andAbove60YearLessThan(Integer value) {
            addCriterion("above_60_year <", value, "above60Year");
            return (Criteria) this;
        }

        public Criteria andAbove60YearLessThanOrEqualTo(Integer value) {
            addCriterion("above_60_year <=", value, "above60Year");
            return (Criteria) this;
        }

        public Criteria andAbove60YearIn(List<Integer> values) {
            addCriterion("above_60_year in", values, "above60Year");
            return (Criteria) this;
        }

        public Criteria andAbove60YearNotIn(List<Integer> values) {
            addCriterion("above_60_year not in", values, "above60Year");
            return (Criteria) this;
        }

        public Criteria andAbove60YearBetween(Integer value1, Integer value2) {
            addCriterion("above_60_year between", value1, value2, "above60Year");
            return (Criteria) this;
        }

        public Criteria andAbove60YearNotBetween(Integer value1, Integer value2) {
            addCriterion("above_60_year not between", value1, value2, "above60Year");
            return (Criteria) this;
        }

        public Criteria andAbove70YearIsNull() {
            addCriterion("above_70_year is null");
            return (Criteria) this;
        }

        public Criteria andAbove70YearIsNotNull() {
            addCriterion("above_70_year is not null");
            return (Criteria) this;
        }

        public Criteria andAbove70YearEqualTo(Integer value) {
            addCriterion("above_70_year =", value, "above70Year");
            return (Criteria) this;
        }

        public Criteria andAbove70YearNotEqualTo(Integer value) {
            addCriterion("above_70_year <>", value, "above70Year");
            return (Criteria) this;
        }

        public Criteria andAbove70YearGreaterThan(Integer value) {
            addCriterion("above_70_year >", value, "above70Year");
            return (Criteria) this;
        }

        public Criteria andAbove70YearGreaterThanOrEqualTo(Integer value) {
            addCriterion("above_70_year >=", value, "above70Year");
            return (Criteria) this;
        }

        public Criteria andAbove70YearLessThan(Integer value) {
            addCriterion("above_70_year <", value, "above70Year");
            return (Criteria) this;
        }

        public Criteria andAbove70YearLessThanOrEqualTo(Integer value) {
            addCriterion("above_70_year <=", value, "above70Year");
            return (Criteria) this;
        }

        public Criteria andAbove70YearIn(List<Integer> values) {
            addCriterion("above_70_year in", values, "above70Year");
            return (Criteria) this;
        }

        public Criteria andAbove70YearNotIn(List<Integer> values) {
            addCriterion("above_70_year not in", values, "above70Year");
            return (Criteria) this;
        }

        public Criteria andAbove70YearBetween(Integer value1, Integer value2) {
            addCriterion("above_70_year between", value1, value2, "above70Year");
            return (Criteria) this;
        }

        public Criteria andAbove70YearNotBetween(Integer value1, Integer value2) {
            addCriterion("above_70_year not between", value1, value2, "above70Year");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}