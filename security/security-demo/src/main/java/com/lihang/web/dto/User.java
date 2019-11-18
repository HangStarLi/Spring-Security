package com.lihang.web.dto;

import com.fasterxml.jackson.annotation.JsonView;

public class User {
    public interface UserSimpleView{}
    public interface UserDetailView extends UserSimpleView{}

    private String username;
    private String password;
    private Integer age;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }
    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }
    @JsonView(UserSimpleView.class)
    public Integer getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
