package com.lihang.security.core.validate.code.sms;

import com.lihang.security.core.properties.SecurityProperties;
import com.lihang.security.core.validate.code.ValidateCode;
import com.lihang.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsValidateCodeGenerator")
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public ValidateCode generate(ServletWebRequest request) {
       String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
       return new ValidateCode(code,securityProperties.getCode().getSms().getExpireInt());
    }
}
