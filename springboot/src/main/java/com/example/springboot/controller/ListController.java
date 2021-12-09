package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Goods;
import com.example.springboot.entity.List;
import com.example.springboot.entity.Man;
import com.example.springboot.mapper.ListMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cus")
public class ListController {

    @Resource
    ListMapper listMapper;

    @PostMapping("/cusGoods")
    public Result<?> save(@RequestBody List list) {
        if(list.getCreateTime() == null){
            return Result.error("-1", "没有输入购买时间");
        }
        listMapper.insert(list);
        return Result.success();
    }

    @GetMapping("/cusList")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String cusIdSearch,
                              @RequestParam(defaultValue = "") String goodsIdSearch) {
        LambdaQueryWrapper<List> wrapper = Wrappers.<List>lambdaQuery();
        if(StrUtil.isNotBlank(goodsIdSearch)){
            wrapper.eq(List::getGoodsId,goodsIdSearch);
        }
        if(StrUtil.isNotBlank(cusIdSearch)){
            wrapper.eq(List::getUserId,cusIdSearch);
        }
        Page<List> GoodsPage = listMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(GoodsPage);
    }



}
