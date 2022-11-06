package com.yoyo.chilema_server.controller;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.HollowThread;
import com.yoyo.chilema_server.pojo.UserAccount;
import com.yoyo.chilema_server.pojo.UserHollowText;
import com.yoyo.chilema_server.pojo.UserIDWithHollowID;
import com.yoyo.chilema_server.service.HollowThreadService;
import com.yoyo.chilema_server.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HollowController {
    @Autowired
    HollowThreadService hollowThreadService;
    @Autowired
    UserAccountService userAccountService;

    @CrossOrigin
    @PostMapping("/api/hollow/post")
    R post(@RequestBody HollowThread hollowThread,HttpServletRequest request)
    {
        String token =getTokenFromHeader(request);
        UserAccount userAccount= userAccountService.getUserByToken(token);
        if (userAccount==null)
        {
            return R.error();
        }
        hollowThread.setUserId(userAccount.getId());
        hollowThread.setSenderName(userAccount.getNickname());
        return hollowThreadService.post(hollowThread);
    }

    @CrossOrigin
    @PostMapping("/api/hollow/getHollowByDesc")
    R getHollowByDesc(@RequestBody Integer page)
    {//不需要传递Text，也不需要传递userid，get方法已清洗数据
        return hollowThreadService.getHollowByDesc(page);
    }
    @CrossOrigin
    @PostMapping("/api/hollow/getHollowByAsc")
    R getHollowByAsc(@RequestBody Integer page)
    {//不需要传递Text，也不需要传递userid，get方法已清洗数据
        return hollowThreadService.getHollowByAsc(page);
    }
    @CrossOrigin
    @PostMapping("/api/hollow/getSingleHollow")
    R getSingleHollow(@RequestBody UserIDWithHollowID userIDWithHollowID)
    {
        return hollowThreadService.getSingleHollow(userIDWithHollowID.getHollowID(), userIDWithHollowID.getUserID());
    }
    @CrossOrigin
    @PostMapping("/api/hollow/reply")
    R reply(@RequestBody UserHollowText userHollowText)
    {
        return hollowThreadService.reply(userHollowText);
    }

    @CrossOrigin
    @PostMapping("/api/hollow/setLike")
    R setLike(@RequestBody UserIDWithHollowID userIDWithHollowID)
    {
        return hollowThreadService.setLike(userIDWithHollowID.getHollowID(), userIDWithHollowID.getUserID());
    }
    @CrossOrigin
    @PostMapping("/api/hollow/cancelLike")
    R cancelLike(@RequestBody UserIDWithHollowID userIDWithHollowID)
    {
        return hollowThreadService.cancelLike(userIDWithHollowID.getHollowID(), userIDWithHollowID.getUserID());
    }

    public String getTokenFromHeader(HttpServletRequest request)
    {
        return request.getHeader("userToken");
    }
}
