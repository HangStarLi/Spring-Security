package com.lihang.security.core.validate.code;

import com.lihang.security.core.properties.SecurityProperties;
import com.lihang.security.core.validate.code.image.ImageValidateCodeGenerator;
import com.lihang.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.lihang.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator(){//返回值Bean的key是方法名
        System.out.println("imageValidateCodeGenerator");
        ImageValidateCodeGenerator generator = new ImageValidateCodeGenerator();
        generator.setSecurityProperties(securityProperties);
        return generator;
    }
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }
}
