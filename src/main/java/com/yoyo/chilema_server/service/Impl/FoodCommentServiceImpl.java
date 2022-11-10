package com.yoyo.chilema_server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.mapper.FoodCommentMapper;
import com.yoyo.chilema_server.pojo.Food;
import com.yoyo.chilema_server.pojo.FoodComment;
import com.yoyo.chilema_server.pojo.UserAccount;
import com.yoyo.chilema_server.service.FoodCommentService;
import com.yoyo.chilema_server.service.FoodService;
import com.yoyo.chilema_server.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/26 22:32
 * @package: com.yoyo.chilema_server.service.Impl
 * @Version: 1.0
 */
@Service
@Slf4j
public class FoodCommentServiceImpl implements FoodCommentService {

    @Autowired
    FoodService foodService;
    @Autowired
    private FoodCommentMapper foodCommentMapper;
    @Autowired
    UserAccountService userAccountService;
    public String getTokenFromHeader(HttpServletRequest request)
    {
        return request.getHeader("userToken");
    }
    @Override
    public R addComment(FoodComment foodComment,HttpServletRequest request)
    {
        //加入评价的时候同时会更改food表中的rate
        try
        {

            String token=getTokenFromHeader(request);
            UserAccount userAccount = (UserAccount) userAccountService.getRByToken(token).getData();

            QueryWrapper<FoodComment> listWrapper=new QueryWrapper<>();
            listWrapper.eq("food_id",foodComment.getFoodId());
            List<FoodComment> list =foodCommentMapper.selectList(listWrapper);//对该食物的总评价List
            FoodComment sqlComment=null;
            Float peopleRate=0F;
            for (FoodComment i : list)
            {
                if (Objects.equals(i.getUserId(), userAccount.getId())) {
                    sqlComment=i;
                }

                peopleRate+=i.getRate();
            }

            if (sqlComment==null)
            {
                if(foodCommentMapper.insert(foodComment) > 0)
                {
                    Food food = (Food) foodService.selectFoodById(foodComment.getFoodId()).getData();
                    peopleRate+= foodComment.getRate();
                    peopleRate=peopleRate/(list.size()+1);
                    food.setRate(peopleRate);
                    foodService.updateFood(food);
                    return R.success();
                }
                else
                {
                    return R.error();
                }
            }
            else
            {
                peopleRate-= sqlComment.getRate();
                sqlComment.setRate(foodComment.getRate());
                sqlComment.setContent(foodComment.getContent());
                if (foodCommentMapper.updateById(sqlComment)>0)
                {
                    Food food = (Food) foodService.selectFoodById(foodComment.getFoodId()).getData();
                    peopleRate+= foodComment.getRate();
                    peopleRate=peopleRate/list.size();
                    food.setRate(peopleRate);
                    foodService.updateFood(food);
                    return R.success();
                }
                else
                {
                    return R.error();
                }
            }
        }catch (Exception e)
        {
            return R.error();
        }

    }

    @Override
    public R deleteMyselfComment(Long foodId ,HttpServletRequest request)
    {
        try {
            String token=getTokenFromHeader(request);
            UserAccount userAccount = userAccountService.getUserByToken(token);
            QueryWrapper<FoodComment> wrapper =new QueryWrapper<>();
            wrapper.eq("user_id",userAccount.getId());
            wrapper.eq("food_id",foodId);
            if (foodCommentMapper.delete(wrapper) >0)
            {
                QueryWrapper<FoodComment> listWrapper=new QueryWrapper<>();
                listWrapper.eq("food_id",foodId);
                List<FoodComment> list =foodCommentMapper.selectList(listWrapper);//对该食物的总评价List
                Float peopleRate=0F;
                for (FoodComment i : list)
                {
                    peopleRate+=i.getRate();
                }
                peopleRate=peopleRate/list.size();
                Food food = (Food) foodService.selectFoodById(foodId).getData();
                food.setRate(peopleRate);
                foodService.updateFood(food);
                return R.success();
            }
            else
            {
                return R.error();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }


    }

    @Override
    public R getCommentList(Long foodId)
    {
        QueryWrapper<FoodComment> wrapper=new QueryWrapper<>();
        wrapper.eq("food_id",foodId);
        List<FoodComment> list =foodCommentMapper.selectList(wrapper);
        try
        {
            return R.success(list);
        }
        catch (Exception e)
        {
            return R.error();
        }
    }
}
