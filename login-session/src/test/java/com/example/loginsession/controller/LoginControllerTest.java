package com.example.loginsession.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @author 小白i
 * @date 2020/7/23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class LoginControllerTest {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Test
    public void getUserInfo() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}