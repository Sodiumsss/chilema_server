package com.yoyo.chilema_server.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class UserIDWithHollowID {
    @JsonSerialize(using = ToStringSerializer.class)
    Long userID;
    @JsonSerialize(using = ToStringSerializer.class)
    Long hollowID;
}
