package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * @Author: admin
 * @Description: sky-take-out
 * @Date: Created in 2025/5/13 20:44
 */


public interface UserService {

    User wxLogin(UserLoginDTO userLoginDTO);

}
