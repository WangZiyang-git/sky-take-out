package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: admin
 * @Description: sky-take-out
 * @Date: Created in 2025/5/15 15:05
 */

@Mapper
public interface ShoppingCartMapper {

    List<ShoppingCart> list(ShoppingCart shoppingCart);
    @Update("update  shopping_cart set number =#{number} where id =#{id}")
    void updateNumberById(ShoppingCart shoppingCart);
    @Delete("delete from shopping_cart where  user_id=#{userId}")
    void deleteNumberById(Long currentId);

}
