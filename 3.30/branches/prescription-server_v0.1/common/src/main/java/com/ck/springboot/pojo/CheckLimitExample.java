package com.ck.springboot.pojo;

import java.util.ArrayList;
import java.util.List;

public class CheckLimitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CheckLimitExample() {
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

        public Criteria andRuleNameIsNull() {
            addCriterion("rule_name is null");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNotNull() {
            addCriterion("rule_name is not null");
            return (Criteria) this;
        }

        public Criteria andRuleNameEqualTo(String value) {
            addCriterion("rule_name =", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotEqualTo(String value) {
            addCriterion("rule_name <>", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThan(String value) {
            addCriterion("rule_name >", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("rule_name >=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThan(String value) {
            addCriterion("rule_name <", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThanOrEqualTo(String value) {
            addCriterion("rule_name <=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLike(String value) {
            addCriterion("rule_name like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotLike(String value) {
            addCriterion("rule_name not like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameIn(List<String> values) {
            addCriterion("rule_name in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotIn(List<String> values) {
            addCriterion("rule_name not in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameBetween(String value1, String value2) {
            addCriterion("rule_name between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotBetween(String value1, String value2) {
            addCriterion("rule_name not between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andLowerboundIsNull() {
            addCriterion("lowerbound is null");
            return (Criteria) this;
        }

        public Criteria andLowerboundIsNotNull() {
            addCriterion("lowerbound is not null");
            return (Criteria) this;
        }

        public Criteria andLowerboundEqualTo(Double value) {
            addCriterion("lowerbound =", value, "lowerbound");
            return (Criteria) this;
        }

        public Criteria andLowerboundNotEqualTo(Double value) {
            addCriterion("lowerbound <>", value, "lowerbound");
            return (Criteria) this;
        }

        public Criteria andLowerboundGreaterThan(Double value) {
            addCriterion("lowerbound >", value, "lowerbound");
            return (Criteria) this;
        }

        public Criteria andLowerboundGreaterThanOrEqualTo(Double value) {
            addCriterion("lowerbound >=", value, "lowerbound");
            return (Criteria) this;
        }

        public Criteria andLowerboundLessThan(Double value) {
            addCriterion("lowerbound <", value, "lowerbound");
            return (Criteria) this;
        }

        public Criteria andLowerboundLessThanOrEqualTo(Double value) {
            addCriterion("lowerbound <=", value, "lowerbound");
            return (Criteria) this;
        }

        public Criteria andLowerboundIn(List<Double> values) {
            addCriterion("lowerbound in", values, "lowerbound");
            return (Criteria) this;
        }

        public Criteria andLowerboundNotIn(List<Double> values) {
            addCriterion("lowerbound not in", values, "lowerbound");
            return (Criteria) this;
        }

        public Criteria andLowerboundBetween(Double value1, Double value2) {
            addCriterion("lowerbound between", value1, value2, "lowerbound");
            return (Criteria) this;
        }

        public Criteria andLowerboundNotBetween(Double value1, Double value2) {
            addCriterion("lowerbound not between", value1, value2, "lowerbound");
            return (Criteria) this;
        }

        public Criteria andUpperboundIsNull() {
            addCriterion("upperbound is null");
            return (Criteria) this;
        }

        public Criteria andUpperboundIsNotNull() {
            addCriterion("upperbound is not null");
            return (Criteria) this;
        }

        public Criteria andUpperboundEqualTo(Double value) {
            addCriterion("upperbound =", value, "upperbound");
            return (Criteria) this;
        }

        public Criteria andUpperboundNotEqualTo(Double value) {
            addCriterion("upperbound <>", value, "upperbound");
            return (Criteria) this;
        }

        public Criteria andUpperboundGreaterThan(Double value) {
            addCriterion("upperbound >", value, "upperbound");
            return (Criteria) this;
        }

        public Criteria andUpperboundGreaterThanOrEqualTo(Double value) {
            addCriterion("upperbound >=", value, "upperbound");
            return (Criteria) this;
        }

        public Criteria andUpperboundLessThan(Double value) {
            addCriterion("upperbound <", value, "upperbound");
            return (Criteria) this;
        }

        public Criteria andUpperboundLessThanOrEqualTo(Double value) {
            addCriterion("upperbound <=", value, "upperbound");
            return (Criteria) this;
        }

        public Criteria andUpperboundIn(List<Double> values) {
            addCriterion("upperbound in", values, "upperbound");
            return (Criteria) this;
        }

        public Criteria andUpperboundNotIn(List<Double> values) {
            addCriterion("upperbound not in", values, "upperbound");
            return (Criteria) this;
        }

        public Criteria andUpperboundBetween(Double value1, Double value2) {
            addCriterion("upperbound between", value1, value2, "upperbound");
            return (Criteria) this;
        }

        public Criteria andUpperboundNotBetween(Double value1, Double value2) {
            addCriterion("upperbound not between", value1, value2, "upperbound");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdIsNull() {
            addCriterion("check_items_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdIsNotNull() {
            addCriterion("check_items_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdEqualTo(Integer value) {
            addCriterion("check_items_id =", value, "checkItemsId");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdNotEqualTo(Integer value) {
            addCriterion("check_items_id <>", value, "checkItemsId");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdGreaterThan(Integer value) {
            addCriterion("check_items_id >", value, "checkItemsId");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_items_id >=", value, "checkItemsId");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdLessThan(Integer value) {
            addCriterion("check_items_id <", value, "checkItemsId");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdLessThanOrEqualTo(Integer value) {
            addCriterion("check_items_id <=", value, "checkItemsId");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdIn(List<Integer> values) {
            addCriterion("check_items_id in", values, "checkItemsId");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdNotIn(List<Integer> values) {
            addCriterion("check_items_id not in", values, "checkItemsId");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdBetween(Integer value1, Integer value2) {
            addCriterion("check_items_id between", value1, value2, "checkItemsId");
            return (Criteria) this;
        }

        public Criteria andCheckItemsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("check_items_id not between", value1, value2, "checkItemsId");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdIsNull() {
            addCriterion("liver_kidney_dic_id is null");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdIsNotNull() {
            addCriterion("liver_kidney_dic_id is not null");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdEqualTo(Integer value) {
            addCriterion("liver_kidney_dic_id =", value, "liverKidneyDicId");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdNotEqualTo(Integer value) {
            addCriterion("liver_kidney_dic_id <>", value, "liverKidneyDicId");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdGreaterThan(Integer value) {
            addCriterion("liver_kidney_dic_id >", value, "liverKidneyDicId");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("liver_kidney_dic_id >=", value, "liverKidneyDicId");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdLessThan(Integer value) {
            addCriterion("liver_kidney_dic_id <", value, "liverKidneyDicId");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdLessThanOrEqualTo(Integer value) {
            addCriterion("liver_kidney_dic_id <=", value, "liverKidneyDicId");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdIn(List<Integer> values) {
            addCriterion("liver_kidney_dic_id in", values, "liverKidneyDicId");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdNotIn(List<Integer> values) {
            addCriterion("liver_kidney_dic_id not in", values, "liverKidneyDicId");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdBetween(Integer value1, Integer value2) {
            addCriterion("liver_kidney_dic_id between", value1, value2, "liverKidneyDicId");
            return (Criteria) this;
        }

        public Criteria andLiverKidneyDicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("liver_kidney_dic_id not between", value1, value2, "liverKidneyDicId");
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