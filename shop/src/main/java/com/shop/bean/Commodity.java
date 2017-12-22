package com.shop.bean;

import javax.validation.constraints.Pattern;

public class Commodity {
    private Integer cmdtId;

    @Pattern(regexp = "[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,20}",message = "商品名称应该是2到20位中文和英文数字！")
    private String cmdtName;

    private Long cmdtPrice;

    private Integer categoryId;

    private Category category;

    @Override
    public String toString() {
        return "Commodity{" +
                "cmdtId=" + cmdtId +
                ", cmdtName='" + cmdtName + '\'' +
                ", cmdtPrice=" + cmdtPrice +
                ", categoryId=" + categoryId +
                ", category=" + category +
                '}';
    }

    public Commodity() {
        super();
    }

    public Commodity(Integer cmdtId, String cmdtName, Long cmdtPrice, Integer categoryId) {
        super();
        this.cmdtId = cmdtId;
        this.cmdtName = cmdtName;
        this.cmdtPrice = cmdtPrice;
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCmdtId() {
        return cmdtId;
    }

    public void setCmdtId(Integer cmdtId) {
        this.cmdtId = cmdtId;
    }

    public String getCmdtName() {
        return cmdtName;
    }

    public void setCmdtName(String cmdtName) {
        this.cmdtName = cmdtName == null ? null : cmdtName.trim();
    }

    public Long getCmdtPrice() {
        return cmdtPrice;
    }

    public void setCmdtPrice(Long cmdtPrice) {
        this.cmdtPrice = cmdtPrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}