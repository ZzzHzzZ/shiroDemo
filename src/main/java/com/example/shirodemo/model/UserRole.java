package com.example.shirodemo.model;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Author: zhanghaozhe
 * @Date: 2018/11/15 15:31
 */
@Table(name = "u_user_role")
@Data
public class UserRole {
    private Long uid;
    private Long rid;
}
