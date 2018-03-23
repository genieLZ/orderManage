package com.orderManage.pojo;

import java.util.Date;

public class Evaluate {
    private Integer id;

    private Long orderno;

    private String evaluate;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Evaluate(Integer id, Long orderno, String evaluate, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.orderno = orderno;
        this.evaluate = evaluate;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Evaluate() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrderno() {
        return orderno;
    }

    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }

    public String getEvaluate() {
        return evaluate;
    }


    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate == null ? null : evaluate.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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