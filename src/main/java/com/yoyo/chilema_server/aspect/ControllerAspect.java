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

        if (!funcName.equals("validateAndGet"))
        {
            System.out.println(date +"  MethodStart："+funcName);
        }
        else
        {
            Object[] args= jp.getArgs();
            UserAccount userAccount= (UserAccount) args[0];
            System.out.println( date+"  validateAndGet("+userAccount.getUsername()+")");

        }


    }
    @AfterReturning("Pointcut()")
    public void AfterReturn(JoinPoint jp)
    {
        if (!jp.getSignature().getName().equals("validateAndGet"))
        {
            Date date = new Date();
            System.out.println(date+"  MethodEnd："+jp.getSignature().getName());
        }

    }




}
