package com.orderManage.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Skill {
    private Integer id;

    private String skillname;

    private Integer workerId;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;

    public Skill(Integer id, String skillname, Integer workerId, BigDecimal price, Date createTime, Date updateTime) {
        this.id = id;
        this.skillname = skillname;
        this.workerId = workerId;
        this.price = price;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Skill() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname == null ? null : skillname.trim();
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}