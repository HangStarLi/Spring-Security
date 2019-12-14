package com.lihang.security.browser;

import com.lihang.security.browser.authentication.MyAuthenticationFailureHandler;
import com.lihang.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.lihang.security.browser.session.MyExpiredSessionStrategy;
import com.lihang.security.core.authentication.AbstractChannelSecurityConfig;
import com.lihang.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.lihang.security.core.properties.SecurityConstants;
import com.lihang.security.core.properties.SecurityProperties;
import com.lihang.security.core.validate.code.ValidateCodeFilter;
import com.lihang.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
public class SecurityConfirg extends AbstractChannelSecurityConfig {
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private SpringSocialConfigurer mySocialConfig;
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
       // tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
             .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(mySocialConfig)
                .and()
            .sessionManagement()
                //session失效后跳转的URL
                //.invalidSessionUrl("/session/invalid")
                //.invalidSessionUrl(securityProperties.getBrowser().getSession().getSessionInvalidUrl())
                .invalidSessionStrategy(invalidSessionStrategy)
                //最大登陆数量
                .maximumSessions(securityProperties.getBrowser().getSession().getMaxmumSessions())
                //session达到最大数量时，阻止后面的用户登陆
                //.maxSessionsPreventsLogin(true)
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().getMaxSessionPreventsLogin())
                //第一个用户被挤下后，记录跳转到/session/invalid这个请求之前的请求
                //.expiredSessionStrategy(new MyExpiredSessionStrategy())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
               .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRemermberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
            .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        "/user/regist",
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()
                ).permitAll()//跳过这些请求，不验证
               .anyRequest()
               .authenticated()
               .and().csrf().disable();
 /*       http.httpBasic()//默认普通登陆
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();*/
    }
}
