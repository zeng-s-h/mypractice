package com.example.loginsession.mapper;

import com.example.loginsession.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author 小白i
 * @date 2020/7/23
 */
@Repository
public interface UserMapper {

    User getUserInfo(Integer id);

}
