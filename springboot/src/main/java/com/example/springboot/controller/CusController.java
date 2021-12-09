package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Cus;
import com.example.springboot.entity.Man;
import com.example.springboot.mapper.CusMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cus")
public class CusController {

    @Resource
    CusMapper cusMapper;

    @PostMapping("/cusLogin")
    public Result<?> cusLogin(@RequestBody Cus cus) {
        Cus res = cusMapper.selectOne(Wrappers.<Cus>lambdaQuery().eq(Cus::getCusName, cus.getCusName()).eq(Cus::getPassword, cus.getPassword()));
        if(res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        return Result.success(res);
    }

    @PostMapping("/cusRegister")
    public Result<?> cusRegister(@RequestBody Cus cus) {
        Cus res = cusMapper.selectOne(Wrappers.<Cus>lambdaQuery().eq(Cus::getCusName, cus.getCusName()));// 比较
        if(res != null) {//当输入的用户名已经存在（相等），就不能注册这个名字
            return Result.error("-1", "用户名重复");
        }
        if(cus.getMoney() == null) {
            cus.setMoney(0);
        }
        cusMapper.insert(cus);//插入到数据库
        return Result.success();
    }

    @PutMapping("/cusPerson")
    public Result<?> cusUpdate(@RequestBody Cus cus) {//把前台传过来的json转化为java对象user，要把它插入数据库就需要mapper
        cusMapper.updateById(cus);//通过userMapper进行数据库的新增
        return Result.success();
    }




}
