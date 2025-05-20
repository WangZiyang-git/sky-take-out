package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetMealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * @Author: admin
 * @Description: sky-take-out
 * @Date: Created in 17:04 2025/5/12
 */
import org.springframework.stereotype.Service;

@Service
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Autowired
    private SetmealMapper setmealMapper;


    public void save(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealDishMapper.insert(setmealDTO);
        Long setmealId =setmeal.getId();
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });
        setmealDishMapper.insertBatch(setmealDishes);
    }

    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> setmeals=setmealDishMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(setmeals.getTotal(),setmeals.getResult());
    }

    @Override
    public List<Setmeal> list(Setmeal setmeal) {
        return Collections.emptyList();
    }

    @Override
    public Integer countByCategoryId(Long id) {
        return 0;
    }

    public List<DishItemVO> getDishItemById(Long id) {
        return setmealDishMapper.getDishItemBySetmealId(id);
    }
}
