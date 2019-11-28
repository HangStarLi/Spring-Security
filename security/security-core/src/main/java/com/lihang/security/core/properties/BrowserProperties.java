package com.lihang.security.core.properties;

import org.springframework.boot.autoconfigure.session.SessionProperties;
import sun.security.util.SecurityConstants;

public class BrowserProperties {
    private String loginPage = "/defaultLogin.html";
    private LoginResponseType loginType = LoginResponseType.JSON;
    private int remermberMeSeconds = 3600;

    public int getRemermberMeSeconds() {
        return remermberMeSeconds;
    }

    public void setRemermberMeSeconds(int remermberMeSeconds) {
        this.remermberMeSeconds = remermberMeSeconds;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String signUpUrl) {
        this.loginPage = signUpUrl;
    }
}
