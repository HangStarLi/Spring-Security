package com.lihang.web.aspect;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.lihang.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("aspect: 执行之前");
        //可获取参数的值
        Object[] objects = pjp.getArgs();
        for (Object o:objects) {
            System.out.println(o);
        }
        Object result = pjp.proceed();
        System.out.println("aspect: 方法返回值："+ReflectionToStringBuilder.toString(result,ToStringStyle.MULTI_LINE_STYLE));
        System.out.println("aspect:  切面执行之后");
        return result;
    }
}
