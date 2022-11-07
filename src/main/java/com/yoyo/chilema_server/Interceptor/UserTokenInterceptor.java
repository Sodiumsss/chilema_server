package com.yoyo.chilema_server.Interceptor;

import com.yoyo.chilema_server.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class UserTokenInterceptor implements HandlerInterceptor
{

    @Resource
    UserAccountService userAccountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, @NotNull Object handler) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");

        String token=request.getHeader("userToken");
        if (token==null)//不是由客户端发起的请求
        {
            return false;
        }

        if (token.equals("login")||token.equals("verifyUsername"))//由客户端发起的请求，但没有装入token
        {
            return true;
        }
        if(token.equals("forgetPW")||token.equals("create"))//由客户端发起的请求，但没有装入token
        {
            return true;
        }


        //验证
        boolean inRedis=userAccountService.verify(token);
        log.info("RequestPass:"+inRedis+"|"+token);
        return inRedis;
    }

}
