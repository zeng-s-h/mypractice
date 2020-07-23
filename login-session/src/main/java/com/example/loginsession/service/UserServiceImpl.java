package com.example.loginsession.service;

import com.example.loginsession.entity.User;
import com.example.loginsession.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小白i
 * @date 2020/7/18
 */
@Service
public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    public User addUser(final User user) {
        userMapper.saveUser(user);
        return user;
    }

    public User getUserInfo(Integer id) {
        return userMapper.getUserInfo(id);
    }

}
