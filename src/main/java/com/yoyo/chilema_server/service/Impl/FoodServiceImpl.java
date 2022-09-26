package com.yoyo.chilema_server.service.Impl;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.mapper.FoodMapper;
import com.yoyo.chilema_server.pojo.Food;
import com.yoyo.chilema_server.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/26 22:33
 * @package: com.yoyo.chilema_server.service.Impl
 * @Version: 1.0
 */
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public R addFood(Food food) {
        if(foodMapper.insert(food) > 0) {
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }
}
