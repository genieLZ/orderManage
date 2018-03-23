package com.orderManage.vo;

import java.util.List;

/**
 * Create by LZ
 */
public class WorkerEvaluateVo {

    private String name;

    private String phone;

    private Integer status;

    private List<EvaluateVo> evaluateVoList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<EvaluateVo> getEvaluateVoList() {
        return evaluateVoList;
    }

    public void setEvaluateVoList(List<EvaluateVo> evaluateVoList) {
        this.evaluateVoList = evaluateVoList;
    }
}
