package com.example.shirodemo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhanghaozhe
 * @Date: 2018/11/15 15:30
 */
@Table(name = "u_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String pswd;
    private Date createTime;
    private Date lastLoginTime;
    private Integer status;

    @Transient
    List<Role> roles;
}
