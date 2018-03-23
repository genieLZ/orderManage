package com.orderManage.pojo;

import java.util.Date;

public class Worker {
    private Integer id;

    private String username;

    private String password;

    private Integer age;

    private Integer status;

    private String phone;

    private Date createTime;

    private Date updateTime;

    public Worker(Integer id, String username, String password, Integer age, Integer status, String phone, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.status = status;
        this.phone = phone;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Worker() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
        this.phone = phone == null ? null : phone.trim();
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