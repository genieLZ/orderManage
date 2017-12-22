package com.shop.bean;

import java.util.ArrayList;
import java.util.List;

public class CommodityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommodityExample() {
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

        public Criteria andCmdtIdIsNull() {
            addCriterion("cmdt_id is null");
            return (Criteria) this;
        }

        public Criteria andCmdtIdIsNotNull() {
            addCriterion("cmdt_id is not null");
            return (Criteria) this;
        }

        public Criteria andCmdtIdEqualTo(Integer value) {
            addCriterion("cmdt_id =", value, "cmdtId");
            return (Criteria) this;
        }

        public Criteria andCmdtIdNotEqualTo(Integer value) {
            addCriterion("cmdt_id <>", value, "cmdtId");
            return (Criteria) this;
        }

        public Criteria andCmdtIdGreaterThan(Integer value) {
            addCriterion("cmdt_id >", value, "cmdtId");
            return (Criteria) this;
        }

        public Criteria andCmdtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cmdt_id >=", value, "cmdtId");
            return (Criteria) this;
        }

        public Criteria andCmdtIdLessThan(Integer value) {
            addCriterion("cmdt_id <", value, "cmdtId");
            return (Criteria) this;
        }

        public Criteria andCmdtIdLessThanOrEqualTo(Integer value) {
            addCriterion("cmdt_id <=", value, "cmdtId");
            return (Criteria) this;
        }

        public Criteria andCmdtIdIn(List<Integer> values) {
            addCriterion("cmdt_id in", values, "cmdtId");
            return (Criteria) this;
        }

        public Criteria andCmdtIdNotIn(List<Integer> values) {
            addCriterion("cmdt_id not in", values, "cmdtId");
            return (Criteria) this;
        }

        public Criteria andCmdtIdBetween(Integer value1, Integer value2) {
            addCriterion("cmdt_id between", value1, value2, "cmdtId");
            return (Criteria) this;
        }

        public Criteria andCmdtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cmdt_id not between", value1, value2, "cmdtId");
            return (Criteria) this;
        }

        public Criteria andCmdtNameIsNull() {
            addCriterion("cmdt_name is null");
            return (Criteria) this;
        }

        public Criteria andCmdtNameIsNotNull() {
            addCriterion("cmdt_name is not null");
            return (Criteria) this;
        }

        public Criteria andCmdtNameEqualTo(String value) {
            addCriterion("cmdt_name =", value, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtNameNotEqualTo(String value) {
            addCriterion("cmdt_name <>", value, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtNameGreaterThan(String value) {
            addCriterion("cmdt_name >", value, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtNameGreaterThanOrEqualTo(String value) {
            addCriterion("cmdt_name >=", value, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtNameLessThan(String value) {
            addCriterion("cmdt_name <", value, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtNameLessThanOrEqualTo(String value) {
            addCriterion("cmdt_name <=", value, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtNameLike(String value) {
            addCriterion("cmdt_name like", value, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtNameNotLike(String value) {
            addCriterion("cmdt_name not like", value, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtNameIn(List<String> values) {
            addCriterion("cmdt_name in", values, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtNameNotIn(List<String> values) {
            addCriterion("cmdt_name not in", values, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtNameBetween(String value1, String value2) {
            addCriterion("cmdt_name between", value1, value2, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtNameNotBetween(String value1, String value2) {
            addCriterion("cmdt_name not between", value1, value2, "cmdtName");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceIsNull() {
            addCriterion("cmdt_price is null");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceIsNotNull() {
            addCriterion("cmdt_price is not null");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceEqualTo(Long value) {
            addCriterion("cmdt_price =", value, "cmdtPrice");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceNotEqualTo(Long value) {
            addCriterion("cmdt_price <>", value, "cmdtPrice");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceGreaterThan(Long value) {
            addCriterion("cmdt_price >", value, "cmdtPrice");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("cmdt_price >=", value, "cmdtPrice");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceLessThan(Long value) {
            addCriterion("cmdt_price <", value, "cmdtPrice");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceLessThanOrEqualTo(Long value) {
            addCriterion("cmdt_price <=", value, "cmdtPrice");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceIn(List<Long> values) {
            addCriterion("cmdt_price in", values, "cmdtPrice");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceNotIn(List<Long> values) {
            addCriterion("cmdt_price not in", values, "cmdtPrice");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceBetween(Long value1, Long value2) {
            addCriterion("cmdt_price between", value1, value2, "cmdtPrice");
            return (Criteria) this;
        }

        public Criteria andCmdtPriceNotBetween(Long value1, Long value2) {
            addCriterion("cmdt_price not between", value1, value2, "cmdtPrice");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
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