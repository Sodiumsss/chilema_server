package com.yoyo.chilema_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yoyo.chilema_server.pojo.HollowReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface HollowReplyMapper extends BaseMapper<HollowReply> {
}
