package com.lihang.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lihang.security.browser.support.SimpleResponse;
import com.lihang.security.core.properties.LoginResponseType;
import com.lihang.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        logger.error("登陆失败");
        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=utf-8");
            //objectMapper.writeValueAsString将一个对象转换为json，SimpleResponse(自己创建的)只输出简单格式的错误
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
            logger.debug("返回json格式数据");
        }else{
            super.onAuthenticationFailure(request,response,e);
            logger.debug("返回默认格式数据redirect");
        }

    }
}
