package com.example.shirodemo.dao;

import com.example.shirodemo.config.Mapper;
import com.example.shirodemo.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: zhanghaozhe
 * @Date: 2018/11/15 15:34
 */
public interface UserMapper extends Mapper<User> {

    User selectAuthByUserName(@Param("userName") String userName);
}
