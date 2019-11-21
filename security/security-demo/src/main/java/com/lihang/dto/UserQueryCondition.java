package com.lihang.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserQueryCondition {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户年龄范围起始值")
    private Integer age;
    @ApiModelProperty(value = "用户年龄范围终止值")
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
