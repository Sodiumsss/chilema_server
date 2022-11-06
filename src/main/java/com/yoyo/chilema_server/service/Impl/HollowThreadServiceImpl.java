package com.yoyo.chilema_server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.mapper.HollowThreadMapper;
import com.yoyo.chilema_server.pojo.HollowThread;
import com.yoyo.chilema_server.service.HollowThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HollowThreadServiceImpl implements HollowThreadService {
    @Autowired
    private HollowThreadMapper hollowThreadMapper;

    @Override
    public R post(HollowThread hollowThread) {
        if(hollowThreadMapper.insert(hollowThread) > 0) {
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
        iPage=hollowThreadMapper.selectPage(iPage,queryWrapper);
        List<HollowThread> list = iPage.getRecords();
        for (HollowThread i :list)
        {
            i.setText(null);
            i.setUserId(null);
        }
        return R.success(null,list);
    }
    @Override
    public R getHollowByAsc(Integer page) {
        IPage<HollowThread> iPage=new Page<>(page,5);
        iPage=hollowThreadMapper.selectPage(iPage,null);
        List<HollowThread> list = iPage.getRecords();
        for (HollowThread i :list)
        {
            i.setText(null);
            i.setUserId(null);
        }
        return R.success(null,list);
    }

    @Override
    public R getSingleHollow(Long id) {

       HollowThread hollowThread= hollowThreadMapper.selectById(id);
       if (hollowThread!=null)
       {
           return R.success(hollowThread);
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
}
