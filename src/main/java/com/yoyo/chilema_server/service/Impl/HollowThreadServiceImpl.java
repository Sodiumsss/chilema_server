package com.yoyo.chilema_server.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.mapper.HollowReplyMapper;
import com.yoyo.chilema_server.mapper.HollowThreadMapper;
import com.yoyo.chilema_server.pojo.HollowReply;
import com.yoyo.chilema_server.pojo.HollowThread;
import com.yoyo.chilema_server.pojo.HollowThreadWithReply;
import com.yoyo.chilema_server.pojo.UserHollowText;
import com.yoyo.chilema_server.service.HollowThreadService;
import com.yoyo.chilema_server.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HollowThreadServiceImpl implements HollowThreadService {
    @Autowired
    private HollowThreadMapper hollowThreadMapper;
    @Autowired
    private HollowReplyMapper hollowReplyMapper;
    @Autowired
    private RedisUtils redisUtils;
    public static final String HollowStarSet = "Star::Set::";//Star::Set::1111 (1111)为贴ID
    public static final String HollowClickNumber = "Click::Number::";//Click::Number::1111 (1111)为贴ID

    @Override
    public R post(HollowThread hollowThread) {
        if(hollowThreadMapper.insert(hollowThread) > 0)
        {
            redisUtils.set(HollowClickNumber+hollowThread.getId(),"0");
            return  R.success();
        } else {
            return  R.error();
        }
    }

    @Override
    public R getHollowByDesc(Integer page) {
        IPage<HollowThread> iPage=new Page<>(page,5);
        QueryWrapper<HollowThread> queryWrapper =new QueryWrapper<>();
        queryWrapper.select().orderByDesc("create_time");
        String size=String.valueOf(hollowThreadMapper.selectCount(null));
        iPage=hollowThreadMapper.selectPage(iPage,queryWrapper);
        List<HollowThread> list = iPage.getRecords();
        for (HollowThread i :list)
        {
            i.setText(null);
            i.setUserId(null);
            String threadClicks=redisUtils.get(HollowClickNumber+i.getId());
            Long threadLikes=redisUtils.sSize(HollowStarSet+i.getId());
            i.setReply(getReplies(i.getId()).size());
            if (threadClicks!=null)
            {
                Integer clicks=Integer.valueOf(threadClicks);
                i.setClicks(clicks);
            }
            else
            {
                i.setClicks(0);
            }
            if (threadLikes!=null)
            {
                Integer likes= Math.toIntExact(threadLikes);
                i.setLikes(likes);
            }
            else
            {
                i.setClicks(0);
                i.setLikes(0);
            }
        }
        return R.success(size,list);
    }


    @Override
    public R getSingleHollow(Long tid,Long userId) {
       HollowThread hollowThread= hollowThreadMapper.selectById(tid);
       if (hollowThread!=null)
       {
           redisUtils.incr(HollowClickNumber+hollowThread.getId());
           Long threadLikes=redisUtils.sSize(HollowStarSet+hollowThread.getId());
           hollowThread.setBeLike(redisUtils.sIsMember(HollowStarSet + hollowThread.getId(), String.valueOf(userId)));
           if (threadLikes!=null)
           {
               hollowThread.setLikes(Math.toIntExact(threadLikes));
           }
           else
           {
               hollowThread.setLikes(0);
           }
           hollowThread.setReply(getReplies(tid).size());
           HollowThreadWithReply hollowThreadWithReply=new HollowThreadWithReply();
           hollowThreadWithReply.setHollowThread(hollowThread);
           hollowThreadWithReply.setHollowReplyList(getReplies(tid));
           return R.success(hollowThreadWithReply);
       }
       return R.error();
    }

    @Override
    public R deleteById(Long id) {
        if(hollowThreadMapper.deleteById(id) > 0) {
            return R.success();
        } else {
            return R.error();
        }
    }

    @Override
    public R edit(HollowThread hollowThread) {
        return null;
    }

    @Override
    public R setLike(Long tid,Long userId) {
       Long state= redisUtils.sAdd(HollowStarSet+tid, String.valueOf(userId));
       if (state!=0)
       {
           return R.success();
       }
       return R.error();
    }

    @Override
    public R cancelLike(Long tid,Long userId) {
        Long state= redisUtils.sRemove(HollowStarSet+tid, String.valueOf(userId));
        if (state!=0)
        {
            return R.success();
        }
        return R.error();
    }

    @Override
    public List<HollowReply> getReplies(Long tid) {
        QueryWrapper<HollowReply> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("thread_id",tid);
        return hollowReplyMapper.selectList(queryWrapper);
    }

    @Override
    public R reply(UserHollowText userHollowText) {
        HollowReply hollowReply=new HollowReply();
        hollowReply.setThreadId(userHollowText.getThreadID());
        hollowReply.setReplyId(userHollowText.getUserID());
        hollowReply.setText(userHollowText.getText());
        hollowReply.setSenderName(userHollowText.getSenderName());
        if (hollowReplyMapper.insert(hollowReply)>0)
        {
           return R.success();
        }
        return R.error();
    }
}
