package com.yoyo.chilema_server.pojo.noSQL;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class UserHollowText {
    @JsonSerialize(using = ToStringSerializer.class)
    Long userID;
    @JsonSerialize(using = ToStringSerializer.class)
    Long threadID;
    String senderName;
    String text;
}
