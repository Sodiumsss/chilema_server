package com.yoyo.chilema_server.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.chilema_server.mapper.HollowReplyMapper;
import com.yoyo.chilema_server.mapper.HollowThreadMapper;
import com.yoyo.chilema_server.pojo.HollowReply;
import com.yoyo.chilema_server.pojo.HollowThread;
import com.yoyo.chilema_server.pojo.noSQL.MyData;
import com.yoyo.chilema_server.service.HollowThreadService;
import com.yoyo.chilema_server.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Configuration
public class ApplicationService implements ApplicationRunner {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    HollowThreadMapper hollowThreadMapper;
    @Autowired
    HollowReplyMapper hollowReplyMapper;
    @Autowired
    MyData myData;
    @Override
    public void run(ApplicationArguments args) {
        log.info("图片真实路径："+
                System.getProperty("user.dir") + System.getProperty("file.separator") + "img" +
                System.getProperty("file.separator") + "foodPic");
        redisUtils.init();

        log.info("数据类初始化中...");

        myData.setHollowSize(hollowThreadMapper.selectCount(null));//获取帖子总数
        log.info("树洞总帖子："+myData.getHollowSize());


        List<HollowReply> list = hollowReplyMapper.selectList(null);//获取全部回复

        HashMap<Long, Integer> hashMap = new HashMap<>();

        for (HollowReply i : list)
        {
            if (hashMap.containsKey(i.getThreadId()))
            {
                hashMap.put(i.getThreadId(),hashMap.get(i.getThreadId())+1);
            }
            else
            {
                hashMap.put(i.getThreadId(),1);
            }
        }
        myData.setHollowId_Replies(hashMap);
        log.info("数据类初始化完毕...");
    }



}
