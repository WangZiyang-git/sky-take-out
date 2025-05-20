package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

/**
 * @Author: admin
 * @Description: sky-take-out
 * @Date: Created in 16:28 2025/5/10
 */
public interface CategoryService {

    void update(CategoryDTO categoryDTO);

    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void startorstop(Integer status, Long id);

    void save(CategoryDTO categoryDTO);


    List<Category> list(Integer type);

    void delete(Long id);
}
