package com.example.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 小白i
 */
@RestController
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    /**
     * 假如这个客户端要提供一个getUser的方法
     *
     * @return Map<String, Object>
     */
    @GetMapping(value = "/getUser")
    @ResponseBody
    public Map<String, Object> getUser(@RequestParam Integer id) {
        Map<String, Object> data = new HashMap<>(4);
        data.put("id", id);
        data.put("userName", "admin");
        data.put("from", "provider-A");
        return data;
    }
}
