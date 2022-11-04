package com.yoyo.chilema_server.service.Impl;

import com.yoyo.chilema_server.handler.HollowThreadHandler;
import com.yoyo.chilema_server.mapper.HollowThreadMapper;
import com.yoyo.chilema_server.pojo.HollowThread;
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

        HollowThread hollowThread=new HollowThread();
        hollowThread.setText("666");
        hollowThread.setUserid(1L);
        hollowThread.setTitle("2");
        hollowThreadMapper.insert(hollowThread);

    }
}
