package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Goods;
import com.example.springboot.mapper.GoodsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/man/manGoods")
public class GoodsController {

    @Resource
    GoodsMapper goodsMapper;

    @PostMapping
    public Result<?> save(@RequestBody Goods goods) {
        goodsMapper.insert(goods);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Goods goods) {
        goodsMapper.updateById(goods);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        goodsMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Goods> wrapper = Wrappers.<Goods>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Goods::getName,search);
        }
        Page<Goods> GoodsPage = goodsMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(GoodsPage);
    }
}
