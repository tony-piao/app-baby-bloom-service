package com.babybloom.web.model.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserAccountExample() {
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

        public Criteria andGuidIsNull() {
            addCriterion("guid is null");
            return (Criteria) this;
        }

        public Criteria andGuidIsNotNull() {
            addCriterion("guid is not null");
            return (Criteria) this;
        }

        public Criteria andGuidEqualTo(Long value) {
            addCriterion("guid =", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotEqualTo(Long value) {
            addCriterion("guid <>", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThan(Long value) {
            addCriterion("guid >", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThanOrEqualTo(Long value) {
            addCriterion("guid >=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThan(Long value) {
            addCriterion("guid <", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThanOrEqualTo(Long value) {
            addCriterion("guid <=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidIn(List<Long> values) {
            addCriterion("guid in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotIn(List<Long> values) {
            addCriterion("guid not in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidBetween(Long value1, Long value2) {
            addCriterion("guid between", value1, value2, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotBetween(Long value1, Long value2) {
            addCriterion("guid not between", value1, value2, "guid");
            return (Criteria) this;
        }

        public Criteria andUserNumberIsNull() {
            addCriterion("userNumber is null");
            return (Criteria) this;
        }

        public Criteria andUserNumberIsNotNull() {
            addCriterion("userNumber is not null");
            return (Criteria) this;
        }

        public Criteria andUserNumberEqualTo(String value) {
            addCriterion("userNumber =", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotEqualTo(String value) {
            addCriterion("userNumber <>", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberGreaterThan(String value) {
            addCriterion("userNumber >", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberGreaterThanOrEqualTo(String value) {
            addCriterion("userNumber >=", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberLessThan(String value) {
            addCriterion("userNumber <", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberLessThanOrEqualTo(String value) {
            addCriterion("userNumber <=", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberLike(String value) {
            addCriterion("userNumber like", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotLike(String value) {
            addCriterion("userNumber not like", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberIn(List<String> values) {
            addCriterion("userNumber in", values, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotIn(List<String> values) {
            addCriterion("userNumber not in", values, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberBetween(String value1, String value2) {
            addCriterion("userNumber between", value1, value2, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotBetween(String value1, String value2) {
            addCriterion("userNumber not between", value1, value2, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("userName is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("userName is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("userName =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("userName <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("userName >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("userName >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("userName <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("userName <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("userName like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("userName not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("userName in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("userName not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("userName between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("userName not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andLifeFlagIsNull() {
            addCriterion("lifeFlag is null");
            return (Criteria) this;
        }

        public Criteria andLifeFlagIsNotNull() {
            addCriterion("lifeFlag is not null");
            return (Criteria) this;
        }

        public Criteria andLifeFlagEqualTo(Byte value) {
            addCriterion("lifeFlag =", value, "lifeFlag");
            return (Criteria) this;
        }

        public Criteria andLifeFlagNotEqualTo(Byte value) {
            addCriterion("lifeFlag <>", value, "lifeFlag");
            return (Criteria) this;
        }

        public Criteria andLifeFlagGreaterThan(Byte value) {
            addCriterion("lifeFlag >", value, "lifeFlag");
            return (Criteria) this;
        }

        public Criteria andLifeFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("lifeFlag >=", value, "lifeFlag");
            return (Criteria) this;
        }

        public Criteria andLifeFlagLessThan(Byte value) {
            addCriterion("lifeFlag <", value, "lifeFlag");
            return (Criteria) this;
        }

        public Criteria andLifeFlagLessThanOrEqualTo(Byte value) {
            addCriterion("lifeFlag <=", value, "lifeFlag");
            return (Criteria) this;
        }

        public Criteria andLifeFlagIn(List<Byte> values) {
            addCriterion("lifeFlag in", values, "lifeFlag");
            return (Criteria) this;
        }

        public Criteria andLifeFlagNotIn(List<Byte> values) {
            addCriterion("lifeFlag not in", values, "lifeFlag");
            return (Criteria) this;
        }

        public Criteria andLifeFlagBetween(Byte value1, Byte value2) {
            addCriterion("lifeFlag between", value1, value2, "lifeFlag");
            return (Criteria) this;
        }

        public Criteria andLifeFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("lifeFlag not between", value1, value2, "lifeFlag");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("openId is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("openId is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("openId =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("openId <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("openId >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("openId >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("openId <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("openId <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("openId like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("openId not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("openId in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("openId not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("openId between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("openId not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andUnionIdIsNull() {
            addCriterion("unionId is null");
            return (Criteria) this;
        }

        public Criteria andUnionIdIsNotNull() {
            addCriterion("unionId is not null");
            return (Criteria) this;
        }

        public Criteria andUnionIdEqualTo(String value) {
            addCriterion("unionId =", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotEqualTo(String value) {
            addCriterion("unionId <>", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdGreaterThan(String value) {
            addCriterion("unionId >", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdGreaterThanOrEqualTo(String value) {
            addCriterion("unionId >=", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdLessThan(String value) {
            addCriterion("unionId <", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdLessThanOrEqualTo(String value) {
            addCriterion("unionId <=", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdLike(String value) {
            addCriterion("unionId like", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotLike(String value) {
            addCriterion("unionId not like", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdIn(List<String> values) {
            addCriterion("unionId in", values, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotIn(List<String> values) {
            addCriterion("unionId not in", values, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdBetween(String value1, String value2) {
            addCriterion("unionId between", value1, value2, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotBetween(String value1, String value2) {
            addCriterion("unionId not between", value1, value2, "unionId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("updateUser is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("updateUser is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(Long value) {
            addCriterion("updateUser =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(Long value) {
            addCriterion("updateUser <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(Long value) {
            addCriterion("updateUser >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(Long value) {
            addCriterion("updateUser >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(Long value) {
            addCriterion("updateUser <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(Long value) {
            addCriterion("updateUser <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<Long> values) {
            addCriterion("updateUser in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<Long> values) {
            addCriterion("updateUser not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(Long value1, Long value2) {
            addCriterion("updateUser between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(Long value1, Long value2) {
            addCriterion("updateUser not between", value1, value2, "updateUser");
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