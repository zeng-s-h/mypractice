package com.example.consumer.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 小白i
 * @date 2020/7/20
 */
@RestController
@RequestMapping("/hystrix/consumer")
public class HystrixConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDefaultUser")
    @RequestMapping("/getUser")
    public Map<String, Object> getUser(@RequestParam("id") Integer id) {
        return restTemplate.getForObject("http://service-provider/getUser?id=" + id, Map.class);
    }

    public Map<String, Object> getDefaultUser(Integer id) {
        System.out.println("熔断，默认回调函数");
        Map<String,Object> data = new HashMap<>(4);
        data.put("id","-1");
        data.put("name","熔断用户");
        data.put("password","123456");
        return data;
    }

}
