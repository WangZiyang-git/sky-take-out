package com.sky.controller.admin;

import com.sky.annotation.AutoFill;
import com.sky.enumeration.OperationType;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: admin
 * @Description: sky-take-out
 * @Date: Created in 21:52 2025/5/12
 */
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags ="店铺相关接口")
@Slf4j
public class ShopController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置店铺营业状态为{}",status ==1 ?"营业中":"打样中");
        redisTemplate.opsForValue().set("shop:status",status.toString());
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus(){
        Integer status= Integer.valueOf((String) redisTemplate.opsForValue().get("shop:status"));
        log.info("设置店铺营业状态为{}",status ==1 ?"营业中":"打样中");
        return Result.success(status);
    }
}
