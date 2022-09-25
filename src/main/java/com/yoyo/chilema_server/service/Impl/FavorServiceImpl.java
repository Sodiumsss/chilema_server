package com.yoyo.chilema_server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.mapper.FavorMapper;
import com.yoyo.chilema_server.pojo.Favor;
import com.yoyo.chilema_server.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/23 12:57
 * @package: com.yoyo.chilema_server.service.Impl
 * @Version: 1.0
 */
@Service
public class FavorServiceImpl implements FavorService {

    @Autowired
    FavorMapper favorMapper;

    @Override
    public R addFavor(Favor favor) {
        if(favorMapper.insert(favor) > 0) {
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    @Override
    public R deleteFavor(int id) {
        if(favorMapper.deleteById(id) > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    @Override
    public R updateFavor(Favor favor) {
        if(favorMapper.updateById(favor) > 0) {
            return R.success("更新成功");
        } else {
            return R.error("更新失败");
        }
    }

    @Override
    public R selectFavor() {
        return R.success("查询成功",favorMapper.selectList(null));
    }

    @Override
    public R selectFavorById(int id) {
        return R.success("查询成功",favorMapper.selectById(id));
    }

    @Override
    public R deleteFavorByUN(String username) {
        QueryWrapper<Favor> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        if(favorMapper.delete(queryWrapper) > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
}
