package com.lihang.security.browser;

import com.lihang.security.browser.session.MyExpiredSessionStrategy;
import com.lihang.security.browser.session.MyInvalidSessionStrategy;
import com.lihang.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
public class BrowserSecurityBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;
    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        System.out.println("URL:++++++++++"+securityProperties.getBrowser().getSession().getSessionInvalidUrl());
        return new MyInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());

    }
    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new MyExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }
}
