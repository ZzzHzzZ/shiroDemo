package com.example.shirodemo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: zhanghaozhe
 * @Date: 2018/11/15 15:29
 */
@Table(name = "u_role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @Transient
    List<Permission> permissions;
}
