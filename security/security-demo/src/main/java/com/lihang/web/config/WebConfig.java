package com.lihang.web.config;

import com.lihang.web.filter.TimeFilter;
import com.lihang.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    TimeInterceptor interceptor;
     //让拦截器生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
   //让过滤器生效
    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        TimeFilter filter = new TimeFilter();
        bean.setFilter(filter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");//过滤所有请求
        bean.setUrlPatterns(urls);
        return bean;
    }
}
