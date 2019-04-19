package com.ck.springboot.pojo;

import java.util.ArrayList;
import java.util.List;

public class PathologyPhysiologyIndicationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PathologyPhysiologyIndicationExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andLactationIsNull() {
            addCriterion("Lactation is null");
            return (Criteria) this;
        }

        public Criteria andLactationIsNotNull() {
            addCriterion("Lactation is not null");
            return (Criteria) this;
        }

        public Criteria andLactationEqualTo(Integer value) {
            addCriterion("Lactation =", value, "lactation");
            return (Criteria) this;
        }

        public Criteria andLactationNotEqualTo(Integer value) {
            addCriterion("Lactation <>", value, "lactation");
            return (Criteria) this;
        }

        public Criteria andLactationGreaterThan(Integer value) {
            addCriterion("Lactation >", value, "lactation");
            return (Criteria) this;
        }

        public Criteria andLactationGreaterThanOrEqualTo(Integer value) {
            addCriterion("Lactation >=", value, "lactation");
            return (Criteria) this;
        }

        public Criteria andLactationLessThan(Integer value) {
            addCriterion("Lactation <", value, "lactation");
            return (Criteria) this;
        }

        public Criteria andLactationLessThanOrEqualTo(Integer value) {
            addCriterion("Lactation <=", value, "lactation");
            return (Criteria) this;
        }

        public Criteria andLactationIn(List<Integer> values) {
            addCriterion("Lactation in", values, "lactation");
            return (Criteria) this;
        }

        public Criteria andLactationNotIn(List<Integer> values) {
            addCriterion("Lactation not in", values, "lactation");
            return (Criteria) this;
        }

        public Criteria andLactationBetween(Integer value1, Integer value2) {
            addCriterion("Lactation between", value1, value2, "lactation");
            return (Criteria) this;
        }

        public Criteria andLactationNotBetween(Integer value1, Integer value2) {
            addCriterion("Lactation not between", value1, value2, "lactation");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionIsNull() {
            addCriterion("liver_function is null");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionIsNotNull() {
            addCriterion("liver_function is not null");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionEqualTo(Integer value) {
            addCriterion("liver_function =", value, "liverFunction");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionNotEqualTo(Integer value) {
            addCriterion("liver_function <>", value, "liverFunction");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionGreaterThan(Integer value) {
            addCriterion("liver_function >", value, "liverFunction");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionGreaterThanOrEqualTo(Integer value) {
            addCriterion("liver_function >=", value, "liverFunction");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionLessThan(Integer value) {
            addCriterion("liver_function <", value, "liverFunction");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionLessThanOrEqualTo(Integer value) {
            addCriterion("liver_function <=", value, "liverFunction");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionIn(List<Integer> values) {
            addCriterion("liver_function in", values, "liverFunction");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionNotIn(List<Integer> values) {
            addCriterion("liver_function not in", values, "liverFunction");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionBetween(Integer value1, Integer value2) {
            addCriterion("liver_function between", value1, value2, "liverFunction");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionNotBetween(Integer value1, Integer value2) {
            addCriterion("liver_function not between", value1, value2, "liverFunction");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowIsNull() {
            addCriterion("liver_function_uparrow is null");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowIsNotNull() {
            addCriterion("liver_function_uparrow is not null");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowEqualTo(Integer value) {
            addCriterion("liver_function_uparrow =", value, "liverFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowNotEqualTo(Integer value) {
            addCriterion("liver_function_uparrow <>", value, "liverFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowGreaterThan(Integer value) {
            addCriterion("liver_function_uparrow >", value, "liverFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowGreaterThanOrEqualTo(Integer value) {
            addCriterion("liver_function_uparrow >=", value, "liverFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowLessThan(Integer value) {
            addCriterion("liver_function_uparrow <", value, "liverFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowLessThanOrEqualTo(Integer value) {
            addCriterion("liver_function_uparrow <=", value, "liverFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowIn(List<Integer> values) {
            addCriterion("liver_function_uparrow in", values, "liverFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowNotIn(List<Integer> values) {
            addCriterion("liver_function_uparrow not in", values, "liverFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowBetween(Integer value1, Integer value2) {
            addCriterion("liver_function_uparrow between", value1, value2, "liverFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andLiverFunctionUparrowNotBetween(Integer value1, Integer value2) {
            addCriterion("liver_function_uparrow not between", value1, value2, "liverFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionIsNull() {
            addCriterion("kidney_function is null");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionIsNotNull() {
            addCriterion("kidney_function is not null");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionEqualTo(Integer value) {
            addCriterion("kidney_function =", value, "kidneyFunction");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionNotEqualTo(Integer value) {
            addCriterion("kidney_function <>", value, "kidneyFunction");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionGreaterThan(Integer value) {
            addCriterion("kidney_function >", value, "kidneyFunction");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionGreaterThanOrEqualTo(Integer value) {
            addCriterion("kidney_function >=", value, "kidneyFunction");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionLessThan(Integer value) {
            addCriterion("kidney_function <", value, "kidneyFunction");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionLessThanOrEqualTo(Integer value) {
            addCriterion("kidney_function <=", value, "kidneyFunction");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionIn(List<Integer> values) {
            addCriterion("kidney_function in", values, "kidneyFunction");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionNotIn(List<Integer> values) {
            addCriterion("kidney_function not in", values, "kidneyFunction");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionBetween(Integer value1, Integer value2) {
            addCriterion("kidney_function between", value1, value2, "kidneyFunction");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionNotBetween(Integer value1, Integer value2) {
            addCriterion("kidney_function not between", value1, value2, "kidneyFunction");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowIsNull() {
            addCriterion("kidney_function_uparrow is null");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowIsNotNull() {
            addCriterion("kidney_function_uparrow is not null");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowEqualTo(Integer value) {
            addCriterion("kidney_function_uparrow =", value, "kidneyFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowNotEqualTo(Integer value) {
            addCriterion("kidney_function_uparrow <>", value, "kidneyFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowGreaterThan(Integer value) {
            addCriterion("kidney_function_uparrow >", value, "kidneyFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowGreaterThanOrEqualTo(Integer value) {
            addCriterion("kidney_function_uparrow >=", value, "kidneyFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowLessThan(Integer value) {
            addCriterion("kidney_function_uparrow <", value, "kidneyFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowLessThanOrEqualTo(Integer value) {
            addCriterion("kidney_function_uparrow <=", value, "kidneyFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowIn(List<Integer> values) {
            addCriterion("kidney_function_uparrow in", values, "kidneyFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowNotIn(List<Integer> values) {
            addCriterion("kidney_function_uparrow not in", values, "kidneyFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowBetween(Integer value1, Integer value2) {
            addCriterion("kidney_function_uparrow between", value1, value2, "kidneyFunctionUparrow");
            return (Criteria) this;
        }

        public Criteria andKidneyFunctionUparrowNotBetween(Integer value1, Integer value2) {
            addCriterion("kidney_function_uparrow not between", value1, value2, "kidneyFunctionUparrow");
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