package com.yoyo.chilema_server.pojo;

import lombok.Data;

import java.util.List;
@Data
public class HollowThreadWithReply {
    HollowThread hollowThread;
    List<HollowReply> hollowReplyList;
}
