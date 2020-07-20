package com.example.zl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小白i
 * @date 2020/7/15
 */
@Configuration
public class TopicRabbitConfig {

    private static final String MAN = "topic.man";

    private static final String WOMAN = "topic.woman";

    /**
     * 定义第一个队列，名称：topic.man
     *
     * @return Queue
     */
    @Bean
    public Queue firstQueue() {
        return new Queue(TopicRabbitConfig.MAN);
    }

    /**
     * 定义一个队列，名称：topic.woman
     *
     * @return Queue
     */
    @Bean
    public Queue secondQueue() {
        return new Queue(TopicRabbitConfig.WOMAN);
    }

    /**
     * topic交换机
     *
     * @return TopicExchange
     */
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 绑定第一个队列
     *
     * @return Binding
     */
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(MAN);
    }

    /**
     * 绑定第二个队列
     *
     * @return Binding
     */
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }

}
