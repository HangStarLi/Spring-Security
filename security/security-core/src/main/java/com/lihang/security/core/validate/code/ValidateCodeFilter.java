package com.lihang.security.core.validate.code;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ValidateCodeFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("myFilter++++++++++++++++++++++++++"+httpServletRequest.getRequestURI());
        if (StringUtils.equals("/authentication/form",httpServletRequest.getRequestURI())
               &&StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")){
           try {
                validate(new ServletWebRequest(httpServletRequest));
           }catch (ValidateCodeExecution e ){
               authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
               return;
           }
       }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession =(ImageCode) sessionStrategy.getAttribute(request,ValidateCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"imageCode");
        if (StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeExecution("验证码不能为空");
        }
        if (codeInRequest == null){
            throw new ValidateCodeExecution("验证码不存在");
        }
        if (codeInSession.isExpired()){
            sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeExecution("验证码已失效");
        }
        if (!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidateCodeExecution("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
