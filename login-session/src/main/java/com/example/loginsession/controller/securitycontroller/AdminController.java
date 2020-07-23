package com.example.loginsession.controller.securitycontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小白i
 * @date 2020/7/23
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello,World!";
    }

    @GetMapping("/login")
    public String login() {
        return "login success";
    }

}
