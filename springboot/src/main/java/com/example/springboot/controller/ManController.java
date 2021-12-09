package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Cus;
import com.example.springboot.entity.Goods;
import com.example.springboot.entity.List;
import com.example.springboot.entity.Man;
import com.example.springboot.mapper.CusMapper;
import com.example.springboot.mapper.ListMapper;
import com.example.springboot.mapper.ManMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController //定义是一个返回json的controller，返回json数据
@RequestMapping("/man") //接口统一的路由
public class ManController {

    @Resource
    CusMapper cusMapper;

    //添加用户信息
    //定义一个post接口把数据加到数据库
    @PostMapping("/manCus")
    public Result<?> cusSave(@RequestBody Cus cus) {//把前台传过来的json转化为java对象cus，要把它插入数据库就需要mapper
        if(cus.getPassword()==null) {
            cus.setPassword("123456");
        }
        cusMapper.insert(cus);//通过cusMapper进行数据库的新增
        return Result.success();

    }

    //显示用户信息
    //定义一个接口把数据从后台拿到前台显示
    @GetMapping("/manCus")
    public Result<?> cusFindPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Cus> wrapper = Wrappers.<Cus>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Cus::getCusName,search);
        }
        Page<Cus> cusPage = cusMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(cusPage);
    }
    //把后台拿到的值放到tableData里面

    //修改用户信息
    //定义接口把数据更新
    @PutMapping("/manCus")
    public Result<?> cusUpdate(@RequestBody Cus cus) {//把前台传过来的json转化为java对象cus，要把它插入数据库就需要mapper
        cusMapper.updateById(cus);//通过cusMapper进行数据库的新增
        return Result.success();
    }


    @Resource
    ManMapper manMapper;

    @PostMapping("/manLogin")
    public Result<?> manLogin(@RequestBody Man man) {
        Man res = manMapper.selectOne(Wrappers.<Man>lambdaQuery().eq(Man::getManName, man.getManName()).eq(Man::getPassword, man.getPassword()));
        if(res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        return Result.success(res);
    }

    @PostMapping("/manRegister")
    public Result<?> manRegister(@RequestBody Man man) {
        Man res = manMapper.selectOne(Wrappers.<Man>lambdaQuery().eq(Man::getManName, man.getManName()));// 比较
        if(res != null) {//当输入的用户名已经存在（相等），就不能注册这个名字
            return Result.error("-1", "用户名重复");
        }
        manMapper.insert(man);//插入到数据库
        return Result.success();
    }

    //修改用户信息
    //定义接口把数据更新
    @PutMapping("/manPerson")
    public Result<?> manUpdate(@RequestBody Man man) {//把前台传过来的json转化为java对象cus，要把它插入数据库就需要mapper
        manMapper.updateById(man);//通过cusMapper进行数据库的新增
        return Result.success();
    }

    @Resource
    ListMapper listMapper;

    @PutMapping("/manList")
    public Result<?> update(@RequestBody List list) {
        listMapper.updateById(list);
        return Result.success();
    }
}
