package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: admin
 * @Description: sky-take-out
 * @Date: Created in 2025/5/13 21:19
 */

@Mapper
public interface UserMapper {

    @Select("select * from user where openid =#{openid}")
    User getByopenid(String openid);

    void insert(User user);
}
