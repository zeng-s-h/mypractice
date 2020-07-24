package com.example.loginsession.controller;

import com.example.loginsession.entity.User;
import com.example.loginsession.service.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 小白i
 * @date 2020/7/18
 */
@RestController
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            session.invalidate();
            return "fail";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        session.setAttribute("user", user);
        return "ok";
    }

    @PostMapping("/user/registry")
    public String registry(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        //插入数据
        //保存session
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        session.setAttribute("user", user);
        return "ok";
    }

    @GetMapping("/user/test")
    public String test(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        //插入数据
        //保存session
        System.out.println("登陆成功");
        return "ok";
    }

    @GetMapping("/getUserInfo")
    public User getUserInfo(@RequestParam("id") Integer id) {
        User userInfo = userService.getUserInfo(id);
        return userInfo;
    }

    @GetMapping("/selectAll")
    public List<User> selectAll() {
        return userService.listUser();
    }

}
