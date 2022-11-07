package com.yoyo.chilema_server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("execution(* com.yoyo.chilema_server.controller.*.*(..))")
    private void Pointcut(){}

    @Before("Pointcut()")
    public void Before(JoinPoint jp)
    {
        String funcName=jp.getSignature().getName();
        log.info(funcName+" - Begin");
    }
    @AfterReturning("Pointcut()")
    public void AfterReturn(JoinPoint jp)
    {
        String funcName=jp.getSignature().getName();
        log.info(funcName+" - End");

    }




}
