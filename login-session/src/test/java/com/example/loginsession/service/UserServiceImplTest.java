package com.example.loginsession.service;

import com.example.loginsession.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @author 小白i
 * @date 2020/7/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void addUser() {
    }

    @Test
    public void listUser() {
        List<User> users = userService.listUser();
        System.out.println("用户集合：" + users);
    }

    @Test
    public void update() {
        userService.update();
    }

    @Test
    public void find() {
    }

    @Test
    public void getUserInfo() {
        userService.update();
        List<User> users = userService.listUser();
        System.out.println(users);
    }
}