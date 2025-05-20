package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: admin
 * @Description: sky-take-out
 * @Date: Created in 20:29 2025/5/11
 */
@Mapper
public interface DishFlavorMapper {

    @AutoFill(OperationType.INSERT)
    void insertBatch(List<DishFlavor> flavorList);

    @Delete("delete  from dish_flavor where dish_id =!{dishId}")
    void deleByDishId(Long l);

    void deleByDishIds(List<Long> dishids);

    @Select("Select * from dish_flavor where dish_id = #{dishId} ")
    List<DishFlavor> getByDishId(Long dishId);
}
