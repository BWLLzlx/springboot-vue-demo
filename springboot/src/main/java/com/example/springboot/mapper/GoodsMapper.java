package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Goods;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> findRandGoods(Integer size);

}
