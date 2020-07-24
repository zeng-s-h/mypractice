package com.example.loginsession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author 小白i
 */

@MapperScan("com.example.loginsession.mapper")
@SpringBootApplication
public class LoginSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginSessionApplication.class, args);
    }

}
