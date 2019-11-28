package com.lihang.security.core.validate.code.sms;

public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机号："+mobile+" 发送了验证码："+code);
    }
}
