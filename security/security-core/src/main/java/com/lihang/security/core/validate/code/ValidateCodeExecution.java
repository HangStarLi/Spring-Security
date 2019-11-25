package com.lihang.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeExecution extends AuthenticationException {
    public ValidateCodeExecution(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeExecution(String msg) {
        super(msg);
    }
}
