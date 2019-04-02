package com.gupao.dal.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MsgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MsgExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMTypeIsNull() {
            addCriterion("m_type is null");
            return (Criteria) this;
        }

        public Criteria andMTypeIsNotNull() {
            addCriterion("m_type is not null");
            return (Criteria) this;
        }

        public Criteria andMTypeEqualTo(Integer value) {
            addCriterion("m_type =", value, "mType");
            return (Criteria) this;
        }

        public Criteria andMTypeNotEqualTo(Integer value) {
            addCriterion("m_type <>", value, "mType");
            return (Criteria) this;
        }

        public Criteria andMTypeGreaterThan(Integer value) {
            addCriterion("m_type >", value, "mType");
            return (Criteria) this;
        }

        public Criteria andMTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("m_type >=", value, "mType");
            return (Criteria) this;
        }

        public Criteria andMTypeLessThan(Integer value) {
            addCriterion("m_type <", value, "mType");
            return (Criteria) this;
        }

        public Criteria andMTypeLessThanOrEqualTo(Integer value) {
            addCriterion("m_type <=", value, "mType");
            return (Criteria) this;
        }

        public Criteria andMTypeIn(List<Integer> values) {
            addCriterion("m_type in", values, "mType");
            return (Criteria) this;
        }

        public Criteria andMTypeNotIn(List<Integer> values) {
            addCriterion("m_type not in", values, "mType");
            return (Criteria) this;
        }

        public Criteria andMTypeBetween(Integer value1, Integer value2) {
            addCriterion("m_type between", value1, value2, "mType");
            return (Criteria) this;
        }

        public Criteria andMTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("m_type not between", value1, value2, "mType");
            return (Criteria) this;
        }

        public Criteria andYwidIsNull() {
            addCriterion("ywid is null");
            return (Criteria) this;
        }

        public Criteria andYwidIsNotNull() {
            addCriterion("ywid is not null");
            return (Criteria) this;
        }

        public Criteria andYwidEqualTo(Long value) {
            addCriterion("ywid =", value, "ywid");
            return (Criteria) this;
        }

        public Criteria andYwidNotEqualTo(Long value) {
            addCriterion("ywid <>", value, "ywid");
            return (Criteria) this;
        }

        public Criteria andYwidGreaterThan(Long value) {
            addCriterion("ywid >", value, "ywid");
            return (Criteria) this;
        }

        public Criteria andYwidGreaterThanOrEqualTo(Long value) {
            addCriterion("ywid >=", value, "ywid");
            return (Criteria) this;
        }

        public Criteria andYwidLessThan(Long value) {
            addCriterion("ywid <", value, "ywid");
            return (Criteria) this;
        }

        public Criteria andYwidLessThanOrEqualTo(Long value) {
            addCriterion("ywid <=", value, "ywid");
            return (Criteria) this;
        }

        public Criteria andYwidIn(List<Long> values) {
            addCriterion("ywid in", values, "ywid");
            return (Criteria) this;
        }

        public Criteria andYwidNotIn(List<Long> values) {
            addCriterion("ywid not in", values, "ywid");
            return (Criteria) this;
        }

        public Criteria andYwidBetween(Long value1, Long value2) {
            addCriterion("ywid between", value1, value2, "ywid");
            return (Criteria) this;
        }

        public Criteria andYwidNotBetween(Long value1, Long value2) {
            addCriterion("ywid not between", value1, value2, "ywid");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andReadedIsNull() {
            addCriterion("readed is null");
            return (Criteria) this;
        }

        public Criteria andReadedIsNotNull() {
            addCriterion("readed is not null");
            return (Criteria) this;
        }

        public Criteria andReadedEqualTo(Byte value) {
            addCriterion("readed =", value, "readed");
            return (Criteria) this;
        }

        public Criteria andReadedNotEqualTo(Byte value) {
            addCriterion("readed <>", value, "readed");
            return (Criteria) this;
        }

        public Criteria andReadedGreaterThan(Byte value) {
            addCriterion("readed >", value, "readed");
            return (Criteria) this;
        }

        public Criteria andReadedGreaterThanOrEqualTo(Byte value) {
            addCriterion("readed >=", value, "readed");
            return (Criteria) this;
        }

        public Criteria andReadedLessThan(Byte value) {
            addCriterion("readed <", value, "readed");
            return (Criteria) this;
        }

        public Criteria andReadedLessThanOrEqualTo(Byte value) {
            addCriterion("readed <=", value, "readed");
            return (Criteria) this;
        }

        public Criteria andReadedIn(List<Byte> values) {
            addCriterion("readed in", values, "readed");
            return (Criteria) this;
        }

        public Criteria andReadedNotIn(List<Byte> values) {
            addCriterion("readed not in", values, "readed");
            return (Criteria) this;
        }

        public Criteria andReadedBetween(Byte value1, Byte value2) {
            addCriterion("readed between", value1, value2, "readed");
            return (Criteria) this;
        }

        public Criteria andReadedNotBetween(Byte value1, Byte value2) {
            addCriterion("readed not between", value1, value2, "readed");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Byte value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Byte value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Byte value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Byte value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Byte> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Byte> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Byte value1, Byte value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Byte value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Byte value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Byte value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Byte value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Byte value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Byte value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Byte> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Byte> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Byte value1, Byte value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Byte value1, Byte value2) {
            addCriterion("priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(Byte value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(Byte value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(Byte value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(Byte value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(Byte value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(Byte value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<Byte> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<Byte> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(Byte value1, Byte value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(Byte value1, Byte value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andMPageIsNull() {
            addCriterion("m_page is null");
            return (Criteria) this;
        }

        public Criteria andMPageIsNotNull() {
            addCriterion("m_page is not null");
            return (Criteria) this;
        }

        public Criteria andMPageEqualTo(Byte value) {
            addCriterion("m_page =", value, "mPage");
            return (Criteria) this;
        }

        public Criteria andMPageNotEqualTo(Byte value) {
            addCriterion("m_page <>", value, "mPage");
            return (Criteria) this;
        }

        public Criteria andMPageGreaterThan(Byte value) {
            addCriterion("m_page >", value, "mPage");
            return (Criteria) this;
        }

        public Criteria andMPageGreaterThanOrEqualTo(Byte value) {
            addCriterion("m_page >=", value, "mPage");
            return (Criteria) this;
        }

        public Criteria andMPageLessThan(Byte value) {
            addCriterion("m_page <", value, "mPage");
            return (Criteria) this;
        }

        public Criteria andMPageLessThanOrEqualTo(Byte value) {
            addCriterion("m_page <=", value, "mPage");
            return (Criteria) this;
        }

        public Criteria andMPageIn(List<Byte> values) {
            addCriterion("m_page in", values, "mPage");
            return (Criteria) this;
        }

        public Criteria andMPageNotIn(List<Byte> values) {
            addCriterion("m_page not in", values, "mPage");
            return (Criteria) this;
        }

        public Criteria andMPageBetween(Byte value1, Byte value2) {
            addCriterion("m_page between", value1, value2, "mPage");
            return (Criteria) this;
        }

        public Criteria andMPageNotBetween(Byte value1, Byte value2) {
            addCriterion("m_page not between", value1, value2, "mPage");
            return (Criteria) this;
        }

        public Criteria andYwNoIsNull() {
            addCriterion("yw_no is null");
            return (Criteria) this;
        }

        public Criteria andYwNoIsNotNull() {
            addCriterion("yw_no is not null");
            return (Criteria) this;
        }

        public Criteria andYwNoEqualTo(String value) {
            addCriterion("yw_no =", value, "ywNo");
            return (Criteria) this;
        }

        public Criteria andYwNoNotEqualTo(String value) {
            addCriterion("yw_no <>", value, "ywNo");
            return (Criteria) this;
        }

        public Criteria andYwNoGreaterThan(String value) {
            addCriterion("yw_no >", value, "ywNo");
            return (Criteria) this;
        }

        public Criteria andYwNoGreaterThanOrEqualTo(String value) {
            addCriterion("yw_no >=", value, "ywNo");
            return (Criteria) this;
        }

        public Criteria andYwNoLessThan(String value) {
            addCriterion("yw_no <", value, "ywNo");
            return (Criteria) this;
        }

        public Criteria andYwNoLessThanOrEqualTo(String value) {
            addCriterion("yw_no <=", value, "ywNo");
            return (Criteria) this;
        }

        public Criteria andYwNoLike(String value) {
            addCriterion("yw_no like", value, "ywNo");
            return (Criteria) this;
        }

        public Criteria andYwNoNotLike(String value) {
            addCriterion("yw_no not like", value, "ywNo");
            return (Criteria) this;
        }

        public Criteria andYwNoIn(List<String> values) {
            addCriterion("yw_no in", values, "ywNo");
            return (Criteria) this;
        }

        public Criteria andYwNoNotIn(List<String> values) {
            addCriterion("yw_no not in", values, "ywNo");
            return (Criteria) this;
        }

        public Criteria andYwNoBetween(String value1, String value2) {
            addCriterion("yw_no between", value1, value2, "ywNo");
            return (Criteria) this;
        }

        public Criteria andYwNoNotBetween(String value1, String value2) {
            addCriterion("yw_no not between", value1, value2, "ywNo");
            return (Criteria) this;
        }

        public Criteria andShortDescIsNull() {
            addCriterion("short_desc is null");
            return (Criteria) this;
        }

        public Criteria andShortDescIsNotNull() {
            addCriterion("short_desc is not null");
            return (Criteria) this;
        }

        public Criteria andShortDescEqualTo(String value) {
            addCriterion("short_desc =", value, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andShortDescNotEqualTo(String value) {
            addCriterion("short_desc <>", value, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andShortDescGreaterThan(String value) {
            addCriterion("short_desc >", value, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andShortDescGreaterThanOrEqualTo(String value) {
            addCriterion("short_desc >=", value, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andShortDescLessThan(String value) {
            addCriterion("short_desc <", value, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andShortDescLessThanOrEqualTo(String value) {
            addCriterion("short_desc <=", value, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andShortDescLike(String value) {
            addCriterion("short_desc like", value, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andShortDescNotLike(String value) {
            addCriterion("short_desc not like", value, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andShortDescIn(List<String> values) {
            addCriterion("short_desc in", values, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andShortDescNotIn(List<String> values) {
            addCriterion("short_desc not in", values, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andShortDescBetween(String value1, String value2) {
            addCriterion("short_desc between", value1, value2, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andShortDescNotBetween(String value1, String value2) {
            addCriterion("short_desc not between", value1, value2, "shortDesc");
            return (Criteria) this;
        }

        public Criteria andReadCountIsNull() {
            addCriterion("read_count is null");
            return (Criteria) this;
        }

        public Criteria andReadCountIsNotNull() {
            addCriterion("read_count is not null");
            return (Criteria) this;
        }

        public Criteria andReadCountEqualTo(Long value) {
            addCriterion("read_count =", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotEqualTo(Long value) {
            addCriterion("read_count <>", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountGreaterThan(Long value) {
            addCriterion("read_count >", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountGreaterThanOrEqualTo(Long value) {
            addCriterion("read_count >=", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountLessThan(Long value) {
            addCriterion("read_count <", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountLessThanOrEqualTo(Long value) {
            addCriterion("read_count <=", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountIn(List<Long> values) {
            addCriterion("read_count in", values, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotIn(List<Long> values) {
            addCriterion("read_count not in", values, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountBetween(Long value1, Long value2) {
            addCriterion("read_count between", value1, value2, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotBetween(Long value1, Long value2) {
            addCriterion("read_count not between", value1, value2, "readCount");
            return (Criteria) this;
        }

        public Criteria andPushCountIsNull() {
            addCriterion("push_count is null");
            return (Criteria) this;
        }

        public Criteria andPushCountIsNotNull() {
            addCriterion("push_count is not null");
            return (Criteria) this;
        }

        public Criteria andPushCountEqualTo(Long value) {
            addCriterion("push_count =", value, "pushCount");
            return (Criteria) this;
        }

        public Criteria andPushCountNotEqualTo(Long value) {
            addCriterion("push_count <>", value, "pushCount");
            return (Criteria) this;
        }

        public Criteria andPushCountGreaterThan(Long value) {
            addCriterion("push_count >", value, "pushCount");
            return (Criteria) this;
        }

        public Criteria andPushCountGreaterThanOrEqualTo(Long value) {
            addCriterion("push_count >=", value, "pushCount");
            return (Criteria) this;
        }

        public Criteria andPushCountLessThan(Long value) {
            addCriterion("push_count <", value, "pushCount");
            return (Criteria) this;
        }

        public Criteria andPushCountLessThanOrEqualTo(Long value) {
            addCriterion("push_count <=", value, "pushCount");
            return (Criteria) this;
        }

        public Criteria andPushCountIn(List<Long> values) {
            addCriterion("push_count in", values, "pushCount");
            return (Criteria) this;
        }

        public Criteria andPushCountNotIn(List<Long> values) {
            addCriterion("push_count not in", values, "pushCount");
            return (Criteria) this;
        }

        public Criteria andPushCountBetween(Long value1, Long value2) {
            addCriterion("push_count between", value1, value2, "pushCount");
            return (Criteria) this;
        }

        public Criteria andPushCountNotBetween(Long value1, Long value2) {
            addCriterion("push_count not between", value1, value2, "pushCount");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andPushToIsNull() {
            addCriterion("push_to is null");
            return (Criteria) this;
        }

        public Criteria andPushToIsNotNull() {
            addCriterion("push_to is not null");
            return (Criteria) this;
        }

        public Criteria andPushToEqualTo(String value) {
            addCriterion("push_to =", value, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushToNotEqualTo(String value) {
            addCriterion("push_to <>", value, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushToGreaterThan(String value) {
            addCriterion("push_to >", value, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushToGreaterThanOrEqualTo(String value) {
            addCriterion("push_to >=", value, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushToLessThan(String value) {
            addCriterion("push_to <", value, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushToLessThanOrEqualTo(String value) {
            addCriterion("push_to <=", value, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushToLike(String value) {
            addCriterion("push_to like", value, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushToNotLike(String value) {
            addCriterion("push_to not like", value, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushToIn(List<String> values) {
            addCriterion("push_to in", values, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushToNotIn(List<String> values) {
            addCriterion("push_to not in", values, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushToBetween(String value1, String value2) {
            addCriterion("push_to between", value1, value2, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushToNotBetween(String value1, String value2) {
            addCriterion("push_to not between", value1, value2, "pushTo");
            return (Criteria) this;
        }

        public Criteria andPushWayIsNull() {
            addCriterion("push_way is null");
            return (Criteria) this;
        }

        public Criteria andPushWayIsNotNull() {
            addCriterion("push_way is not null");
            return (Criteria) this;
        }

        public Criteria andPushWayEqualTo(String value) {
            addCriterion("push_way =", value, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushWayNotEqualTo(String value) {
            addCriterion("push_way <>", value, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushWayGreaterThan(String value) {
            addCriterion("push_way >", value, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushWayGreaterThanOrEqualTo(String value) {
            addCriterion("push_way >=", value, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushWayLessThan(String value) {
            addCriterion("push_way <", value, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushWayLessThanOrEqualTo(String value) {
            addCriterion("push_way <=", value, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushWayLike(String value) {
            addCriterion("push_way like", value, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushWayNotLike(String value) {
            addCriterion("push_way not like", value, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushWayIn(List<String> values) {
            addCriterion("push_way in", values, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushWayNotIn(List<String> values) {
            addCriterion("push_way not in", values, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushWayBetween(String value1, String value2) {
            addCriterion("push_way between", value1, value2, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushWayNotBetween(String value1, String value2) {
            addCriterion("push_way not between", value1, value2, "pushWay");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNull() {
            addCriterion("push_time is null");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNotNull() {
            addCriterion("push_time is not null");
            return (Criteria) this;
        }

        public Criteria andPushTimeEqualTo(Date value) {
            addCriterion("push_time =", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotEqualTo(Date value) {
            addCriterion("push_time <>", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThan(Date value) {
            addCriterion("push_time >", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("push_time >=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThan(Date value) {
            addCriterion("push_time <", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThanOrEqualTo(Date value) {
            addCriterion("push_time <=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeIn(List<Date> values) {
            addCriterion("push_time in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotIn(List<Date> values) {
            addCriterion("push_time not in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeBetween(Date value1, Date value2) {
            addCriterion("push_time between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotBetween(Date value1, Date value2) {
            addCriterion("push_time not between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushStatusIsNull() {
            addCriterion("push_status is null");
            return (Criteria) this;
        }

        public Criteria andPushStatusIsNotNull() {
            addCriterion("push_status is not null");
            return (Criteria) this;
        }

        public Criteria andPushStatusEqualTo(Byte value) {
            addCriterion("push_status =", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotEqualTo(Byte value) {
            addCriterion("push_status <>", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusGreaterThan(Byte value) {
            addCriterion("push_status >", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("push_status >=", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusLessThan(Byte value) {
            addCriterion("push_status <", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusLessThanOrEqualTo(Byte value) {
            addCriterion("push_status <=", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusIn(List<Byte> values) {
            addCriterion("push_status in", values, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotIn(List<Byte> values) {
            addCriterion("push_status not in", values, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusBetween(Byte value1, Byte value2) {
            addCriterion("push_status between", value1, value2, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("push_status not between", value1, value2, "pushStatus");
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