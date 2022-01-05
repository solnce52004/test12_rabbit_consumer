package com.example.test12.service;

import com.example.test12.config.RabbitmqConfig;
import com.example.test12.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j

public class ReceiveService {
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitmqConfig.QUEUE_1)
    public void getObject10(MessageDto msg) {
        log.info("From Queue_1 (listener 1) : {}", msg);
    }

    @RabbitListener(queues = RabbitmqConfig.QUEUE_1)
    public void getObject11(MessageDto msg) {
        log.info("From Queue_1 (listener 2): {}", msg);
    }

    ////
    @RabbitListener(queues = RabbitmqConfig.QUEUE_2)
    public void getObject20(MessageDto msg) {
        log.info("From Queue_2 (listener 1): {}", msg);
    }

    @RabbitListener(queues = RabbitmqConfig.QUEUE_2)
    public void getObject21(MessageDto msg) {
        log.info("From Queue_2 (listener 2): {}", msg);
    }

    /////////////

    public MessageDto receive1() {
        return (MessageDto) rabbitTemplate.receiveAndConvert(RabbitmqConfig.QUEUE_1);
    }

    public MessageDto receive2() {
        return (MessageDto) rabbitTemplate.receiveAndConvert(RabbitmqConfig.QUEUE_2);
    }
}
