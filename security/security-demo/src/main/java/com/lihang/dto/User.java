package com.lihang.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.lihang.volidator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import java.util.Date;

public class User {
    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
    }

    private Integer id;
    @MyConstraint
    private String username;
    @NotBlank
    private String password;
    @Digits(integer = 10, fraction =1)
    private Integer age;
    @Past
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public Integer getId() {
        return id;
    }

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

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
