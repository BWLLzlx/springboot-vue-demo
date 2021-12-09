package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("cus")
@Data
public class Cus {
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    private String cusName;
    private String password;
    private Integer money;
    private String address;
}