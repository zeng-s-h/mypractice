package com.example.zl;

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

/**
 * @author 小白i
 * @date 2020/7/15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class DataSourceTest {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
