package com.lee.star.demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.lee.star.common.constants.RabbitConstants.QUEUE_TEST;

@Slf4j
@Service
public class RabbitReciver {

    @RabbitListener(queues = QUEUE_TEST)
    public void demoRecive(String message) {
        log.info("接收到消息{}", message);
        System.out.println("message reciver:" + message);
    }

}
