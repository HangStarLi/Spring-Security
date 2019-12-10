package com.lihang.security.core.validate.code.impl;

import com.lihang.security.core.validate.code.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /**
     * 收集所有ValidateCodeGenerator的实现
     */
    @Autowired
    private Map<String,ValidateCodeGenerator> validateCodeGeneratorMap ;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void create(ServletWebRequest request) throws IOException, ServletRequestBindingException {

         C validateCode = generate(request);
         logger.info("2--validateCode:"+validateCode);
         save(request,validateCode);
         send(request,validateCode);
    }

    /**
     * 生成校验码
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request){
      String type = getValidateCodeType(request).toString().toLowerCase();
      String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
      ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(generatorName);
      logger.info("3--generatorName:"+generatorName);
      if (validateCodeGenerator == null) {
          throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
      }
      return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 保存校验码
     */
    private void save(ServletWebRequest request,C validateCode){
            sessionStrategy.setAttribute(request,getSessionKey(request),validateCode);
    }

    /**
     * 构建验证码放入Session的key
     */
    private String getSessionKey(ServletWebRequest request){
           return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
    }

    /**
     * 发送验证码，由子类实现
     */
    protected abstract  void send(ServletWebRequest request,C validateCode) throws IOException, ServletRequestBindingException;

    /**
     * 根据请求的Url获取检验码的类型,根据实现这个类的类名前半部分决定
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request){
         String type = StringUtils.substringBefore(getClass().getSimpleName(),"ValidateCodeProcessor");
         return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @SuppressWarnings("unckecked")
    @Override
    public void validate(ServletWebRequest request){
        ValidateCodeType processorType = getValidateCodeType(request);
        String sessionKey = getSessionKey(request);

        C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    processorType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(processorType + "验证码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, sessionKey);
            throw new ValidateCodeException(processorType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, sessionKey);
    }

}


