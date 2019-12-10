package com.lihang.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 提供了一个connectionSignUp后，系统默认生成一个用户，用户唯一ID是connection.getDisplayName;
 */
@Component("connectionSignUp")
public class DemoConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        return connection.getDisplayName();
    }
}
