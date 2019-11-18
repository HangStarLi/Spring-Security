package com.lihang.web.dto;

import javax.jnlp.IntegrationService;

public class UserQueryCondition {
    private String username;
    private Integer age;
    private Integer  ageTo;

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getAgeTo() {
        return ageTo;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
    }
}
