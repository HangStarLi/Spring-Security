package com.lihang.security.core.properties;

public class SmsCodeProperties {
    private Integer length = 6;
    private Integer expireInt = 60;
    private String url ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getExpireInt() {
        return expireInt;
    }

    public void setExpireInt(Integer expireInt) {
        this.expireInt = expireInt;
    }
}
