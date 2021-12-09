package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Category;
import com.example.springboot.mapper.CategoryMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/man/manCategory")
public class CategoryController {

    @Resource
    CategoryMapper categoryMapper;

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Category> wrapper = Wrappers.<Category>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Category::getName,search);
        }
        Page<Category> CategoryPage = categoryMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(CategoryPage);
    }
}
