package com.lihang.security.core.validate.code.sms;

import com.lihang.security.core.properties.SecurityConstants;
import com.lihang.security.core.validate.code.ValidateCode;
import com.lihang.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    @Autowired SmsCodeSender smsCodeSender;
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws IOException, ServletRequestBindingException {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
