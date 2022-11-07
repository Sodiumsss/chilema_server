package com.yoyo.chilema_server.pojo.noSQL;

import com.yoyo.chilema_server.pojo.HollowReply;
import com.yoyo.chilema_server.pojo.HollowThread;
import lombok.Data;

import java.util.List;
@Data
public class HollowThreadWithReply {
    HollowThread hollowThread;
    List<HollowReply> hollowReplyList;
}
