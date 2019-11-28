package com.lihang.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 生成验证码的逻辑
 */
public interface ValidateCodeGenerator {
      ValidateCode generate(ServletWebRequest request) ;
}
