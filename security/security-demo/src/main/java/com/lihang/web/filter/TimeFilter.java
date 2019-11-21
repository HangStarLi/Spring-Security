package com.lihang.web.filter;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/*@Component*/ //用了config中的Bean,添加了过滤规则
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter: time filter init");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter: time filter start");
        long start = new Date().getTime();
        filterChain.doFilter(request,response);
        long end = new Date().getTime();
        System.out.println("filter: time filter: " + (end-start));
        System.out.println("filter: time filter end");
    }
    @Override
    public void destroy() {
        System.out.println("intercepter:  time filter destroy");
    }
}
