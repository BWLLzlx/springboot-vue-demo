package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Book;
import com.example.springboot.mapper.BookMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/man/manBook")
public class BookController {

    @Resource
    BookMapper BookMapper;

    @PostMapping
    public Result<?> save(@RequestBody Book book) {
        BookMapper.insert(book);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Book book) {
        BookMapper.updateById(book);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        BookMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Book::getName,search);
        }
        Page<Book> BookPage = BookMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(BookPage);
    }
}
