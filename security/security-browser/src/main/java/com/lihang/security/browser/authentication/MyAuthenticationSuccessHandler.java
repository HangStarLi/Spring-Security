package com.lihang.security.browser.authentication;

import  com.fasterxml.jackson.databind.ObjectMapper;
import com.lihang.security.core.properties.LoginResponseType;
import com.lihang.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登陆成功");
        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=utf-8");
            //System.out.println(objectMapper.writeValueAsString(authentication));
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
            System.out.println("json");
        }else {
            super.onAuthenticationSuccess(request,response,authentication);
            System.out.println("redirect");
        }

    }
}
