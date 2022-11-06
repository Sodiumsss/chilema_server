package com.yoyo.chilema_server.service.Impl;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.mapper.FoodMapper;
import com.yoyo.chilema_server.pojo.Food;
import com.yoyo.chilema_server.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/26 22:33
 * @package: com.yoyo.chilema_server.service.Impl
 * @Version: 1.0
 */
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public R addFood(Food food) {
        if(foodMapper.insert(food) > 0) {
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    @Override
    public R deleteFoodById(Long id) {
        if(foodMapper.deleteById(id) > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    @Override
    public R updateFood(Food food) {
        if(foodMapper.updateById(food) > 0) {
            return R.success("更新成功");
        } else {
            return R.error("更新失败");
        }
    }

    @Override
    public R selectFoodList() {
        return R.success("",foodMapper.selectList(null));
    }

    @Override
    public R selectFoodListById(Long id) {
        return R.success("",foodMapper.selectById(id));
    }

    @Override
    public R selectFoodListByName(String name) {
        return null;
    }

    @Override
    public R uploadImg(MultipartFile file) {
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "foodPic";
        File realFile = new File(filePath);
        if(!realFile.exists()) {
            realFile.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/foodPic/" + fileName;
        try {
            file.transferTo(dest);
            return R.success(imgPath);
        } catch (IOException e) {
            return R.error("上传失败");
        }
    }


}
