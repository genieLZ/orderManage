package com.orderManage.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by LZ
 */
public class SkillEvaluateVo {

    private String skillName;

    private String workerName;

    private String phone;

    private BigDecimal price;

    private List<EvaluateListVo> evaluateListVoList;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<EvaluateListVo> getEvaluateListVoList() {
        return evaluateListVoList;
    }

    public void setEvaluateListVoList(List<EvaluateListVo> evaluateListVoList) {
        this.evaluateListVoList = evaluateListVoList;
    }
}
