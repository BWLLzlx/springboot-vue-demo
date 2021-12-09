package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@TableName("list")
@Data
public class List {
    @TableId(value = "id" , type = IdType.AUTO)
    private  Integer id;
    private Integer userId;
    private Integer goodsId;
    private String goodsName;
    private Date createTime;
    private BigDecimal price;
    private String status;
}