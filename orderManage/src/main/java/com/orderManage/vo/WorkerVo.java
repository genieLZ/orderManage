package com.orderManage.vo;

import java.util.List;

/**
 * Create by LZ
 */
public class WorkerVo {

    private String username;

    private Integer age;

    private Integer status;

    private String phone;

    private List<SkillListVo> skillList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<SkillListVo> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<SkillListVo> skillList) {
        this.skillList = skillList;
    }
}
