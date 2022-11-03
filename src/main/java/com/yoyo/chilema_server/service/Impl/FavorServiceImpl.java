package com.yoyo.chilema_server.service.Impl;

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
    private FavorMapper favorMapper;

    @Override
    public R addFavor(Favor favor) {
        if(favorMapper.insert(favor) > 0) {
            return R.success();
        } else {
            return R.error();
        }
    }

    @Override
    public R deleteFavorById(Long id) {
        if(favorMapper.deleteById(id) > 0) {
            return R.success();
        } else {
            return R.error();
        }
    }

    @Override
    public R updateFavor(Favor favor) {
        if(favorMapper.updateById(favor) > 0) {
            return R.success();
        } else {
            return R.error();
        }
    }

    @Override
    public R selectFavor() {
        return R.success(null,favorMapper.selectList(null));
    }

    @Override
    public R selectFavorById(Favor favor) {
        return R.success(null,favorMapper.selectById(favor.getId()));
    }

}
