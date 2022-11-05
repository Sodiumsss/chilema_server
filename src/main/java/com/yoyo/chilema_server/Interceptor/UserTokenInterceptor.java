package com.yoyo.chilema_server.Interceptor;

import com.yoyo.chilema_server.service.UserAccountService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class UserTokenInterceptor implements HandlerInterceptor
{


    @Autowired
    UserAccountService userAccountService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, @NotNull Object handler) throws Exception
    {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");

        String token=request.getHeader("userToken");
        if (token==null)//不是由客户端发起的请求
        {
            System.out.println("No Token Rejected");
            return false;
        }
        if (token.equals("login"))//由客户端发起的请求，但没有装入token
        {
            return true;
        }
        //有实际token，验证
        if (!userAccountService.verify(token))
        {//验证失败，拒绝该token
            System.out.println("Token:"+token+" Rejected");
            return false;
        }

        //通过
        System.out.println("Token:"+token+" Accepted");
        return true;
    }

}
