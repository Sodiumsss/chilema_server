package com.yoyo.chilema_server.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Data
public class UsernameToToken {
    private final HashMap<String,String> usernameToToken = new HashMap<>();

}
