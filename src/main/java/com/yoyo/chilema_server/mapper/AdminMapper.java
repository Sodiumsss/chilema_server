package com.yoyo.chilema_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yoyo.chilema_server.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
}
