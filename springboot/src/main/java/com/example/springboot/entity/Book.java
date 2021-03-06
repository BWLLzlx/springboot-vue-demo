package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@TableName("book")
@Data
public class Book {
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    private String name;
    private BigDecimal price;
    private String author;
    private Date createTime;
    private String cover;
}
