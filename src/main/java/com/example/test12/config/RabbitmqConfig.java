package com.example.test12.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j

public class RabbitmqConfig {

    public static final String QUEUE_1 = "queue1";
    public static final String QUEUE_2 = "queue2";

    public static final String DIRECT = "amq.direct";
    public static final String FANOUT = "amq.fanout";
    public static final String TOPIC = "amq.topic";

    @Value("${spring.rabbitmq.host}")
    String host;
    @Value("${spring.rabbitmq.port}")
    int port;
    @Value("${spring.rabbitmq.username}")
    String username;
    @Value("${spring.rabbitmq.password}")
    String password;

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(CachingConnectionFactory connectionFactory) {
//        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QUEUE_1, QUEUE_2);
//        container.setMessageListener(msg -> log.info(new String(msg.getBody())));
//        return container;
//    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC);
    }

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_2);
    }

    //////// direct ///////////
    @Bean
    public Binding directBinding10(){
        return BindingBuilder
                .bind(queue1())
                .to(directExchange())
                .with("log.error");
    }

    @Bean
    public Binding directBinding11(){
        return BindingBuilder
                .bind(queue1())
                .to(directExchange())
                .with("log.warning");
    }

    @Bean
    public Binding directBinding20(){
        return BindingBuilder
                .bind(queue2())
                .to(directExchange())
                .with("log.info");
    }

    //////// fanout ///////////
    @Bean
    public Binding fanoutBinding(){
        return BindingBuilder
                .bind(queue1())
                .to(fanoutExchange());
    }

    //////// topic ///////////
    @Bean
    public Binding topicBinding10(){
        return BindingBuilder
                .bind(queue1())
                .to(topicExchange())
                .with("log.*");
    }

    @Bean
    public Binding topicBinding11(){
        return BindingBuilder
                .bind(queue1())
                .to(topicExchange())
                .with("*.warning");
    }

    @Bean
    public Binding topicBinding20(){
        return BindingBuilder
                .bind(queue2())
                .to(topicExchange())
                .with("log.#");
    }
}
