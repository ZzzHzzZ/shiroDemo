package com.example.shirodemo.controller;

import com.example.shirodemo.dao.RoleMapper;
import com.example.shirodemo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;


@Controller
@Slf4j
public class UserInfoController {

    @Autowired
    private RoleMapper roleMapper;

    //跳转到登录表单页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login() {
        return "need login";
    }

    //登录成功后，跳转的页面
    @RequestMapping("/success")
    @ResponseBody
    public String index(Model model) {
        return "success";
    }

    //未登录，可以访问的页面
    @RequestMapping("/index")
    @ResponseBody
    public String list(Model model) {
        return "index";
    }

    //登陆验证，这里方便url测试，正式上线需要使用POST方式提交
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.GET)
    public String index(User user) {
        if (user.getNickname() != null && user.getPswd() != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getNickname(), user.getPswd());
            Subject currentUser = SecurityUtils.getSubject();
            log.info("对用户[" + user.getNickname() + "]进行登录验证..验证开始");
            try {
                currentUser.login(token);
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    log.info("用户[" + user.getNickname() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    System.out.println("用户[" + user.getNickname() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    return "redirect:/success";
                } else {
                    token.clear();
                    System.out.println("用户[" + user.getNickname() + "]登录认证失败,重新登陆");
                    return "redirect:/login";
                }
            } catch (UnknownAccountException uae) {
                log.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-username wasn't in the system");
            } catch (IncorrectCredentialsException ice) {
                log.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-password didn't match");
            } catch (LockedAccountException lae) {
                log.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-account is locked in the system");
            } catch (AuthenticationException ae) {
                log.error(ae.getMessage());
            }
        }
        return "login";
    }

    /**
     * ajax登录请求接口方式登陆
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(@RequestParam(value = "nickname") String username, @RequestParam(value = "pswd") String password) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");

        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout() {
        return "logout";
    }

    //错误页面展示
    @GetMapping("/403")
    @ResponseBody
    public String error() {
        return "error ok!";
    }

    //管理员功能
    @RequiresPermissions("/admin/add")
    @RequestMapping(value = "/admin/add")
    @ResponseBody
    public String create() {
        return "add success!";
    }

    //用户功能
    @RequiresRoles(value = {"admin","user"})
    @RequestMapping(value = "/user/select")
    @ResponseBody
    public String detail() {
        return "select success";
    }
}