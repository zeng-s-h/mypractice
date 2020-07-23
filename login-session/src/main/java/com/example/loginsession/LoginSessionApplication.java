package com.example.loginsession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.example.loginsession") //扫描的mapper
@SpringBootApplication
public class LoginSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginSessionApplication.class, args);
    }

}
