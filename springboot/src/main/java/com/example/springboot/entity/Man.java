package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("man")
@Data
public class Man {
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    private String manName;
    private String password;
}
