package com.lihang.security.core.properties;

public class SessionProperties {
    //同一个用户在系统中最大的session数量
    private int maxmumSessions = 1;
    //达到最大session时是否阻止新的登陆请求，默认不阻止
    private Boolean maxSessionPreventsLogin = false;
    //session失效时跳转的地址
    private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;

    public int getMaxmumSessions() {
        return maxmumSessions;
    }

    public void setMaxmumSessions(int maxmumSessions) {
        this.maxmumSessions = maxmumSessions;
    }

    public Boolean getMaxSessionPreventsLogin() {
        return maxSessionPreventsLogin;
    }

    public void setMaxSessionPreventsLogin(Boolean maxSessionPreventsLogin) {
        this.maxSessionPreventsLogin = maxSessionPreventsLogin;
    }

    public String getSessionInvalidUrl() {
        return sessionInvalidUrl;
    }

    public void setSessionInvalidUrl(String sessionInvalidUrl) {
        this.sessionInvalidUrl = sessionInvalidUrl;
    }
}
