package com.yoyo.chilema_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HollowThread {
    private Long id;
    private Long userid;
    private String title;
    private String text;
    private String datetime;
}
