package com.example.loginsession.service;

import com.example.loginsession.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 小白i
 * @date 2020/7/18
 */
@Service
public class UserServiceImpl {

    public String registry(User user){
        System.out.println("注册成功");
        return "OK";
    }

    public String login(User user){
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return "fail";
        }
        System.out.println("登陆成功");
        return "OK";
    }

}
