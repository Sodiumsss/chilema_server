package com.yoyo.chilema_server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.mapper.UserAccountMapper;
import com.yoyo.chilema_server.pojo.UserAccount;
import com.yoyo.chilema_server.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户账号服务
 * @Author: Shiro
 * @date: 2022/9/22 15:44
 * @package: com.yoyo.chilema_server.service.Impl
 * @Version: 1.0
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public R addUserAccount(UserAccount userAccount) {
        userAccount.setCredit(0);
        if(userAccountMapper.insert(userAccount) > 0) {
            return  R.success();
        } else {
            return  R.error();
        }
    }

    @Override
    public R deleteUserAccount(UserAccount userAccount) {
        if(userAccountMapper.deleteById(userAccount.getId()) > 0) {
            return  R.success();
        } else {
            return  R.error();
        }
    }

    @Override
    public R updateUserAccount(UserAccount userAccount) {
        if(userAccountMapper.updateById(userAccount) > 0) {
            return  R.success();
        } else {
            return  R.error();
        }
    }

    @Override
    public R selectAllUserAccount() {
        return R.success("",userAccountMapper.selectList(null));
    }

    @Override
    public R selectUserAccountById(Long id) {
        return R.success("",userAccountMapper.selectById(id));
    }

    @Override
    public R login(UserAccount userAccount)
    {
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userAccount.getUsername());
        try {
            UserAccount saved = userAccountMapper.selectOne(queryWrapper);
            if (userAccount.getPassword().equals(saved.getPassword()))
            {
                return  R.success(saved.getNickname());
            }
        }catch (Exception e)
        {
            return R.error();
        }

        return  R.error();
    }


    @Override
    public UserAccount selectUserAccountByUsername(String username) {
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        try
        {
            return userAccountMapper.selectOne(queryWrapper);
        }catch (Exception ignored) {}

        return null;
    }

    @Override
    public UserAccount selectUserBy3P(UserAccount userAccount) {
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userAccount.getUsername());
        queryWrapper.eq("password",userAccount.getPassword());
        queryWrapper.eq("nickname",userAccount.getNickname());
        return userAccountMapper.selectOne(queryWrapper);
    }

    @Override
    public R deleteUserAccountByUN(String username) {
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        if(userAccountMapper.delete(queryWrapper) > 0) {
            return R.success();
        } else
        {
            return R.error();
        }
    }


    @Override
    public R changeUserNickname(UserAccount userAccount)
    {
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userAccount.getUsername());

        try {
            UserAccount saved = userAccountMapper.selectOne(queryWrapper);
            if (userAccount.getPassword().equals(saved.getPassword()))
            {
                saved.setNickname(userAccount.getNickname());
                return updateUserAccount(saved);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }

        return  R.error();
    }

}
