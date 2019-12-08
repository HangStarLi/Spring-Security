package com.lihang.security.browser;


import com.lihang.security.browser.support.SimpleResponse;
import com.lihang.security.browser.support.SocialUserInfo;
import com.lihang.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;
import sun.security.util.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private RequestCache  requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperties securityProperties ;


    /*
    * 当需要身份认证时，跳转到这里
    * 请求是否以.html结束如果是就调到自定义登陆页面
    * 是否配置了登陆页面
    * */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if (savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求："+targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("请跳转到登陆页");
    }
    @Autowired
    private ProviderSignInUtils providerSignInUtils;
     @GetMapping("/social/user")
     public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
         SocialUserInfo socialUserInfo = new SocialUserInfo();
         Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
         socialUserInfo.setProviderId(connection.getKey().getProviderId());
         socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
         socialUserInfo.setNickname(connection.getDisplayName());
         socialUserInfo.setHeadimg(connection.getImageUrl());
         return socialUserInfo;
     }
}
