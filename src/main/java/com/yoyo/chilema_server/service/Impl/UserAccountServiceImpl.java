package com.yoyo.chilema_server.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.mapper.UserAccountMapper;
import com.yoyo.chilema_server.pojo.UserAccount;
import com.yoyo.chilema_server.pojo.noSQL.UsernameToToken;
import com.yoyo.chilema_server.service.UserAccountService;
import com.yoyo.chilema_server.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @description: 用户账号服务
 * @Author: Shiro
 * @date: 2022/9/22 15:44
 * @package: com.yoyo.chilema_server.service.Impl
 * @Version: 1.0
 */
@Slf4j
@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    UsernameToToken usernameToToken;

    //token过期时间：1天(86400秒)
    public static final int TOKEN_EXPIRE = 3600 * 24 ;
    //用户token前缀
    public static final String COOKIE_NAME_TOKEN = "Acc";

    @Override
    public R addUserAccount(UserAccount userAccount) {
        userAccount.setCredit(0);
        if(userAccountMapper.insert(userAccount) > 0) {
            return  R.success("添加成功");
        } else {
            return  R.error("添加失败");
        }
    }

    @Override
    public R deleteUserAccount(UserAccount userAccount) {
        if(userAccountMapper.deleteById(userAccount.getId()) > 0) {
            return  R.success("删除成功");
        } else {
            return  R.error("删除失败");
        }
    }

    @Override
    public R updateUserAccount(UserAccount userAccount) {
        if(userAccountMapper.updateById(userAccount) > 0) {
            return  R.success("更新成功");
        } else {
            return  R.error("更新失败");
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
    public R Login(UserAccount userAccount)
    {
        final String username = userAccount.getUsername();
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("username",username);
        try {
            UserAccount saved = userAccountMapper.selectOne(queryWrapper);//从数据库中拿到与用户名对应的用户行，为空直接抛异常
            if (userAccount.getPassword().equals(saved.getPassword()))
            {
                String token=UUID.randomUUID().toString().replace("-","");//生成唯一token
                String mapToken=usernameToToken.getUsernameToToken().get(username);//从HashMap中取到与用户名对应value
                if (mapToken==null)
                {//为空表示该用户未登录
                    usernameToToken.getUsernameToToken().put(username,token);//放入
                }
                else
                {//已经有人登录过这个账户，把之前那个人的token删掉，并把新token放入map与redis中
                    redisUtils.delete(COOKIE_NAME_TOKEN+"::"+mapToken);
                    usernameToToken.getUsernameToToken().put(saved.getUsername(), token);
                    log.info("重复登录！删除："+mapToken+"更换为："+token);
                }
                redisUtils.set(COOKIE_NAME_TOKEN+"::"+token, JSON.toJSONString(saved),TOKEN_EXPIRE);
                saved.clearSensitiveness();
                return R.success(token,saved);//给客户端返回token与脱敏过后的用户信息
            }
        }catch (Exception e)
        {
            return R.error();
        }
        return R.error();
    }


    @Override
    public R getRByToken(String token)
    {//方法内未实现redis与sql中的用户信息对比，需要在修改处自行更新redis
        if(StringUtils.isEmpty(token))
        {
            log.warn("空token");
            return R.error();
        }
        try
        {
            String json =redisUtils.get(COOKIE_NAME_TOKEN+"::"+token);
            UserAccount redisAccount= JSON.parseObject(json,UserAccount.class);
            redisAccount.clearSensitiveness();
            return R.success(redisAccount);//清除敏感信息后返回R，但redis中保留所有信息
        }
        catch (Exception e)
        {
            log.warn("redis不存在token为"+token+"的信息");
            return R.error();
        }
    }

    @Override
    public UserAccount getUserByToken(String token) {//没有清除敏感信息
        if(StringUtils.isEmpty(token))
        {
            return null;
        }
        String json =redisUtils.get(COOKIE_NAME_TOKEN+"::"+token);
        if (json==null)
        {
            return null;
        }
        return JSON.parseObject(json,UserAccount.class);
    }


    @Override
    public boolean verify(String token) {
        if(StringUtils.isEmpty(token))
        {
            return false;
        }
        String json =redisUtils.get(COOKIE_NAME_TOKEN+"::"+token);
        return json != null;
    }

    @Override
    public void syncRedis()
    {
      HashMap<String,String>a = usernameToToken.getUsernameToToken();//Username - Token

        QueryWrapper<UserAccount> wrapper =new QueryWrapper<>();
        a.forEach((key,value)->
        {
            wrapper.eq("username",key);
            UserAccount userAccount = userAccountMapper.selectOne(wrapper);//得到了数据库中的user，准备更新到redis中
            redisUtils.set(COOKIE_NAME_TOKEN+"::"+value,JSON.toJSONString(userAccount),TOKEN_EXPIRE);
      });
    }

    @Override
    public void setToken(String token, UserAccount userAccount)
    {
        redisUtils.set(COOKIE_NAME_TOKEN+"::"+token, JSON.toJSONString(userAccount),TOKEN_EXPIRE);//存入redis
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
            saved.setNickname(userAccount.getNickname());
            setToken(usernameToToken.getUsernameToToken().get(saved.getUsername()),saved);
            return updateUserAccount(saved);
        }catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }



    @Override
    public R deleteUserAccountById(Long id) {
        if(userAccountMapper.deleteById(id) > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    @Override
    public R getUserCount() {
        return R.success("查询成功",userAccountMapper.selectCount(null));
    }
}
