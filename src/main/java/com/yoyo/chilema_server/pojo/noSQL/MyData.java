package com.yoyo.chilema_server.pojo.noSQL;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
public class MyData {
    Long hollowSize;
    HashMap<Long, Integer> hollowId_Replies;
}
