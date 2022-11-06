package com.yoyo.chilema_server.controller;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.Food;
import com.yoyo.chilema_server.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/26 22:41
 * @package: com.yoyo.chilema_server.controller
 * @Version: 1.0
 */
@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/api/food/test")
    public R test() {
        return R.success("hello2");
    }

    @PostMapping("/api/food/getList")
    @CrossOrigin
    public R getFoodList() {
        return foodService.selectFoodList();
    }

    @PostMapping("/api/food/add")
    @CrossOrigin
    public R addFood(@RequestBody Food food) {
        return foodService.addFood(food);
    }

    @PostMapping("/api/food/delete")
    @CrossOrigin
    public R deleteFood(@RequestBody Long id) {
        return foodService.deleteFoodById(id);
    }

    @PostMapping("/api/food/update")
    @CrossOrigin
    public R updateFood(@RequestBody Food food) {
        return foodService.updateFood(food);
    }

    @PostMapping("/api/food/upload")
    @CrossOrigin
    public R uploadFoodImg(@RequestBody MultipartFile file) {
        return foodService.uploadImg(file);
    }

    @PostMapping("/api/food/uploadById")
    @CrossOrigin
    public R uploadFoodImgById(@RequestParam("file") MultipartFile file,@RequestParam("id")Long id) {
        R uploadImg = foodService.uploadImg(file);
        Food food = new Food();
        food.setId(id);
        food.setPic(uploadImg.getMessage());
        return foodService.updateFood(food);
    }
}
