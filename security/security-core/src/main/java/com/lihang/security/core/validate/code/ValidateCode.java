package com.lihang.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ValidateCode {
    private String code;
    private LocalDateTime expireTime;

    public ValidateCode(String code, Integer expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public ValidateCode() {
    }


    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
