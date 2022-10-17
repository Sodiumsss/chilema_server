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
        if(userAccountMapper.insert(userAccount) > 0) {
            return  R.success("注册成功！");
        } else {
            return  R.error("注册失败！");
        }
    }

    @Override
    public R deleteUserAccount(int id) {
        if(userAccountMapper.deleteById(id) > 0) {
            return  R.success("删除成功！");
        } else {
            return  R.error("删除失败！");
        }
    }

    @Override
    public R updateUserAccount(UserAccount userAccount) {
        if(userAccountMapper.updateById(userAccount) > 0) {
            return  R.success("更新成功！");
        } else {
            return  R.error("更新失败！");
        }
    }

    @Override
    public R selectAllUserAccount() {
        return R.success("查询成功！",userAccountMapper.selectList(null));
    }

    @Override
    public R selectUserAccountById(int id) {
        return R.success("查询成功！",userAccountMapper.selectById(id));
    }

    @Override
    public R login(UserAccount received) {
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",received.getUsername());


        try {
            UserAccount saved = userAccountMapper.selectOne(queryWrapper);
            System.out.println(saved);
            System.out.println(received);

            if (received.getPassword().equals(saved.getPassword()))
            {
                return  R.success("登录成功！");
            }
        }catch (Exception e)
        {
            //e.printStackTrace();
            return R.error("登录失败！");
        }

        return  R.error("登录失败！");
    }

    @Override
    public UserAccount selectUserAccountByUsername(String username) {
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        try {
            return userAccountMapper.selectOne(queryWrapper);
        }catch (Exception ignored){
        }
        return null;
    }

    @Override
    public R deleteUserAccountByUN(String username) {
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        if(userAccountMapper.delete(queryWrapper) > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    @Override
    public R getUserNickname(UserAccount userAccount) {
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userAccount.getUsername());
        queryWrapper.eq("password",userAccount.getPassword());
        UserAccount saved = userAccountMapper.selectOne(queryWrapper);
        if (saved==null)
        {
            return R.error("你的Cookies似乎有些问题，请重新登录！");
        }

        return R.success(saved.getNickname());
    }

    @Override
    public R changeUserNickname(UserAccount userAccount)
    {
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userAccount.getUsername());

        try {
            UserAccount saved = userAccountMapper.selectOne(queryWrapper);
            System.out.println(saved);
            System.out.println(userAccount);
            if (userAccount.getPassword().equals(saved.getPassword()))
            {
                saved.setNickname(userAccount.getNickname());
                return updateUserAccount(saved);
            }
        }catch (Exception e)
        {
            return R.error("更新失败！");
        }

        return  R.error("更新失败！");
    }

}
