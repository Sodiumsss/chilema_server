package com.yoyo.chilema_server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.mapper.HollowReplyMapper;
import com.yoyo.chilema_server.mapper.HollowThreadMapper;
import com.yoyo.chilema_server.pojo.HollowReply;
import com.yoyo.chilema_server.pojo.HollowThread;
import com.yoyo.chilema_server.pojo.noSQL.HollowThreadWithReply;
import com.yoyo.chilema_server.pojo.noSQL.MyData;
import com.yoyo.chilema_server.pojo.noSQL.UserHollowText;
import com.yoyo.chilema_server.service.HollowThreadService;
import com.yoyo.chilema_server.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Slf4j
@Service
public class HollowThreadServiceImpl implements HollowThreadService {
    @Autowired
    private HollowThreadMapper hollowThreadMapper;
    @Autowired
    private HollowReplyMapper hollowReplyMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    MyData myData;

    public static final String HollowStarSet = "Star::Set::";//Star::Set::1111 (1111)为贴ID
    public static final String HollowClickNumber = "Click::Number::";//Click::Number::1111 (1111)为贴ID

    @Override
    public R post(HollowThread hollowThread) {
        if(hollowThreadMapper.insert(hollowThread) > 0)
        {
            redisUtils.set(HollowClickNumber+hollowThread.getId(),"0");
            myData.setHollowSize(myData.getHollowSize()+1);
            log.info("新发帖子，当前总帖子："+myData.getHollowSize());
            return  R.success();
        } else {
            return  R.error();
        }
    }

    @Override
    public R getHollowByDesc(Integer page) {
        IPage<HollowThread> iPage=new Page<>(page,5);
        QueryWrapper<HollowThread> queryWrapper =new QueryWrapper<>();
        queryWrapper.select().orderByDesc("id");
        HashMap<Long,Integer> hollowId_Replies=myData.getHollowId_Replies();

        String size=String.valueOf(myData.getHollowSize());//帖子总数采用组件进行维护

        iPage=hollowThreadMapper.selectPage(iPage,queryWrapper);
        List<HollowThread> list = iPage.getRecords();
        for (HollowThread i :list)
        {
            String threadClicks=redisUtils.get(HollowClickNumber+i.getId());
            Long threadLikes=redisUtils.sSize(HollowStarSet+i.getId());

            Integer replies= hollowId_Replies.get(i.getId());
            if (replies!=null)
            {
                i.setReply(replies);
            }
            if (threadClicks!=null)
            {
                Integer clicks=Integer.valueOf(threadClicks);
                i.setClicks(clicks);
            }

            if (threadLikes!=null)
            {
                Integer likes= Math.toIntExact(threadLikes);
                i.setLikes(likes);
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
    public R deleteMyselfById(Long id) {

        QueryWrapper<HollowReply> wrapper = new QueryWrapper<>();
        wrapper.eq("thread_id",id);
        List<HollowReply> list = hollowReplyMapper.selectList(wrapper);
        for (HollowReply i : list)
        {
            hollowReplyMapper.deleteById(i.getId());//先删评论
        }
        if(hollowThreadMapper.deleteById(id) > 0)//再删帖子
        {
            redisUtils.delete(HollowClickNumber+id);//删redis
            redisUtils.delete(HollowStarSet+id);
            myData.setHollowSize(myData.getHollowSize()-1);//维护size
            HashMap<Long ,Integer> hashMap = myData.getHollowId_Replies();//维护hashmap
            hashMap.remove(id);
            myData.setHollowId_Replies(hashMap);
            return R.success();
        }
        else
        {
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
            Integer replies= myData.getHollowId_Replies().get(userHollowText.getThreadID());
            HashMap<Long,Integer> hashMap= myData.getHollowId_Replies();
            if (replies==null)
            {
                hashMap.put(userHollowText.getThreadID(), 1);
            }
            else
            {
                hashMap.put(userHollowText.getThreadID(),replies+ 1);
            }
            myData.setHollowId_Replies(hashMap);

            HollowThread hollowThread = hollowThreadMapper.selectById(userHollowText.getThreadID());
            hollowThreadMapper.updateById(hollowThread);
           return R.success();
        }
        return R.error();
    }
}
