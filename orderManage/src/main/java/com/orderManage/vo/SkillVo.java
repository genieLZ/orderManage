package com.orderManage.vo;

import java.math.BigDecimal;

/**
 * Create by LZ
 */
public class SkillVo {

    private String skillName;

    private String workerName;

    private BigDecimal price;

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
}
