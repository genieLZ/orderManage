package com.orderManage.vo;

import java.math.BigDecimal;

/**
 * Create by LZ
 */
public class SkillListVo {

    private String skillName;

    private BigDecimal price;

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
