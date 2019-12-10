package com.lihang.security.core.properties;

import org.springframework.boot.autoconfigure.session.SessionProperties;


public class BrowserProperties {
    private String loginPage = com.lihang.security.core.properties.SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
    private String signUpUrl = "/defaultRegist.html";
    private LoginResponseType loginType = LoginResponseType.JSON;
    private com.lihang.security.core.properties.SessionProperties session = new com.lihang.security.core.properties.SessionProperties();
    private int remermberMeSeconds = 3600;

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public int getRemermberMeSeconds() {
        return remermberMeSeconds;
    }

    public void setRemermberMeSeconds(int remermberMeSeconds) {
        this.remermberMeSeconds = remermberMeSeconds;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public com.lihang.security.core.properties.SessionProperties getSession() {
        return session;
    }

    public void setSession(com.lihang.security.core.properties.SessionProperties session) {
        this.session = session;
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
