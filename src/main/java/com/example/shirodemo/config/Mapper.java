package com.example.shirodemo.config;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @Author: zhanghaozhe
 * @Date: 2018/11/15 15:25
 */
public interface Mapper<T>
        extends
        BaseMapper<T>,
        ExampleMapper<T>,
        IdsMapper<T>,
        ConditionMapper<T>,
        InsertListMapper<T> {
}