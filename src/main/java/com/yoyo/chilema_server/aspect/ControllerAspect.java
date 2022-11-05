package com.yoyo.chilema_server.aspect;

import com.yoyo.chilema_server.pojo.UserAccount;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Aspect
@Component
public class ControllerAspect {

    @Pointcut("execution(* com.yoyo.chilema_server.controller.*.*(..))")
    private void Pointcut(){}

    @Before("Pointcut()")
    public void Before(JoinPoint jp)
    {
        String funcName=jp.getSignature().getName();
        Date date = new Date();
        System.out.println(date +" Start："+funcName);
    }
    @AfterReturning("Pointcut()")
    public void AfterReturn(JoinPoint jp)
    {
        Date date = new Date();
        System.out.println(date+" End："+ jp.getSignature().getName());
    }




}
