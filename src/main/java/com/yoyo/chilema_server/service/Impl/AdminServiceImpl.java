package com.yoyo.chilema_server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.mapper.AdminMapper;
import com.yoyo.chilema_server.pojo.Admin;
import com.yoyo.chilema_server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/27 22:52
 * @package: com.yoyo.chilema_server.service.Impl
 * @Version: 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public R login(Admin admin) {
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",admin.getUsername());

        try {
            Admin currentAdmin = adminMapper.selectOne(queryWrapper);
            if(currentAdmin.getPassword().equals(admin.getPassword())) {
                return R.success("登录成功");
            }
        } catch (Exception e) {
            return R.error("登录失败");
        }

        return R.error("登录失败");
    }
}
