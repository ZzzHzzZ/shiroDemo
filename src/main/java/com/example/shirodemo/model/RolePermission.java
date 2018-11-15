package com.example.shirodemo.model;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Author: zhanghaozhe
 * @Date: 2018/11/15 15:29
 */
@Table(name = "u_role_permission")
@Data
public class RolePermission {
    private Long rid;
    private Long pid;
}
