package com.example.zlclient.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 小白i
 * @date 2020/7/15
 */
@Component
@RabbitListener(queues = "topic.woman")
public class TopicTotalReceiver {

    @RabbitHandler
    public void process(Map<Object, Object> msg) {
        System.out.println("TopicTotalReceiver消费者收到消息  : " + msg.toString());
    }

}
