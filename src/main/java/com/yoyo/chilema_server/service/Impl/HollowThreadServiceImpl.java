package com.yoyo.chilema_server.service.Impl;

import com.yoyo.chilema_server.mapper.HollowThreadMapper;
import com.yoyo.chilema_server.service.HollowThreadService;
import com.yoyo.chilema_server.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HollowThreadServiceImpl implements HollowThreadService {
    @Resource
    UserAccountService userAccountService;
    @Autowired
    private HollowThreadMapper hollowThreadMapper;

    @Override
    public void test() {

    }
}
