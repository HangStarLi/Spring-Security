package com.lihang.security.browser;

import com.lihang.security.browser.authentication.MyAuthenticationFailureHandler;
import com.lihang.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.lihang.security.core.properties.SecurityProperties;
import com.lihang.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfirg extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        http.addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .formLogin()//表单登陆
                .loginPage("/authentication/require")//登陆请求
                .loginProcessingUrl("/authentication/form")//表单中的提交URL
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require"
                        ,securityProperties.getBrowser().getLoginPage()
                        ,"/code/image"
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
