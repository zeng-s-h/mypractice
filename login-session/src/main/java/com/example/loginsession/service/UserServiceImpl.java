package com.example.loginsession.service;

import com.example.loginsession.dynamicdatasource.DataSourceSelector;
import com.example.loginsession.dynamicdatasource.DynamicDataSourceEnum;
import com.example.loginsession.entity.User;
import com.example.loginsession.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @DataSourceSelector(value = DynamicDataSourceEnum.SLAVE)
    public List<User> listUser() {
        return userMapper.selectAll();
    }

    @DataSourceSelector(value = DynamicDataSourceEnum.MASTER)
    @Transactional(value = "dataSourceTx", rollbackFor = Exception.class)
    public void update() {
        User user = new User();
        user.setId(Long.parseLong("1"));
        user.setUsername("小宝贝");
        userMapper.updateUserById(user);
        //throw new RuntimeException("master回滚数据");
    }

    @DataSourceSelector(value = DynamicDataSourceEnum.SLAVE)
    public User find() {
        User user = new User();
        user.setId(Long.parseLong("1196978513958141952"));
        return userMapper.getUserInfo(1);
    }

    public User getUserInfo(Integer id) {
        return userMapper.getUserInfo(id);
    }

}
