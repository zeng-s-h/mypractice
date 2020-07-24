package com.example.loginsession.mapper;

import com.example.loginsession.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 小白i
 * @date 2020/7/23
 */
@Repository
public interface UserMapper {

    /**
     * 根据id查询用户信息
     *
     * @param id 主键id
     * @return User
     */
    User getUserInfo(Integer id);

    /**
     * 插入用户
     *
     * @param user 用户
     */
    void saveUser(User user);

    List<User> selectAll();

    void updateUserById(User user);
}
