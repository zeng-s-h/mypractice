package com.example.zl.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 小白i
 * @date 2020/7/15
 */
@RestController
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String DIRECT_EXCHANGE_NAME = "TestDirectExchange";

    private static final String DIRECT_ROUTING_KEY = "TestDirectRouting";

    private static final String TOPIC_EXCHANGE_NAME = "topicExchange";

    private static final String TOPIC_ROUTING_KEY_MAN = "topic.man";

    private static final String TOPIC_ROUTING_KEY_WOMAN = "topic.woman";


    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String msg = "test direct message";
        sendMsg(msg, DIRECT_EXCHANGE_NAME, DIRECT_ROUTING_KEY);
        return "ok";
    }

    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        String msg = "test topic exchange";
        sendMsg(msg, TOPIC_EXCHANGE_NAME, TOPIC_ROUTING_KEY_MAN);
        return "ok";
    }

    private void sendMsg(String msg, String exchangeName, String routingKey) {
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", msg);
        map.put("createTime", createTime);
        //将消息携带绑定键值：交换机名称 发送到交换机的路由键
        rabbitTemplate.convertAndSend(exchangeName, routingKey, map);
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        String msg = "test topic exchange";
        sendMsg(msg, TOPIC_EXCHANGE_NAME, TOPIC_ROUTING_KEY_WOMAN);
        return "ok";
    }
}
