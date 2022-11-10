package com.yoyo.chilema_server.service;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.Food;
import org.springframework.web.multipart.MultipartFile;

public interface FoodService {
    R addFood(Food food);
    R deleteFoodById(Long id);
    R updateFood(Food food);
    R selectFoodList();
    R selectFoodById(Long id);
    R selectFoodListByName(String name);
    R uploadImg(MultipartFile file);
}
