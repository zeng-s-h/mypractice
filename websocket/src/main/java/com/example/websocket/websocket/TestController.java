package com.example.websocket.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 小白i
 * @date 2020/7/27
 */
@Controller
@RequestMapping("/static")
public class TestController {
    @RequestMapping("/showExplain")
    public String re() {
        return "message";
    }
}
