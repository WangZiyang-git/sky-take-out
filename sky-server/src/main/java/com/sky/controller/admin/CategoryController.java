package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: admin
 * @Description: sky-take-out
 * @Date: Created in 16:26 2025/5/10
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PutMapping
    @ApiOperation("修改分类")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分类分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询：{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用")
    public Result startorstop(@PathVariable Integer status, Long id){
        categoryService.startorstop(status,id);
        return Result.success();
    }

    @PostMapping
    @ApiOperation("新增分类")
    public Result add(CategoryDTO categoryDTO){
        categoryService.save(categoryDTO);
        return  Result.success();
    }

    @GetMapping("/list/list")
    @ApiOperation("根据类型查询")
    public Result<List<Category>> list(Integer type){
        List<Category> list =categoryService.list(type);
        return Result.success(list);
    }

    @DeleteMapping
    @ApiOperation("根据id删除")
    public Result delete(Long id){
        categoryService.delete(id);
        return Result.success();
    }
}
