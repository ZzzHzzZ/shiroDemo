package com.example.shirodemo.realm;

import com.example.shirodemo.dao.UserMapper;
import com.example.shirodemo.model.Permission;
import com.example.shirodemo.model.Role;
import com.example.shirodemo.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: zhanghaozhe
 * @Date: 2018/11/15 13:39
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        System.out.println("登录时拿到的token为：" + token.toString());
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        User user = userMapper.selectAuthByUserName(username);
        System.out.println("------->>userInfo=" + user);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPswd(),
                getName()
        );
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationIfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User userInfo = (User) principals.getPrimaryPrincipal();
        for (Role role : userInfo.getRoles()) {
            authorizationInfo.addRole(role.getName());
            for (Permission permission : role.getPermissions()) {
                authorizationInfo.addStringPermission(permission.getUrl());
            }
        }
        return authorizationInfo;
    }
}
