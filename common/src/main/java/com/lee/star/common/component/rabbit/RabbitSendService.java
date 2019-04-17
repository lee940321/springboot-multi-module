package com.lee.star.common.component.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.lee.star.common.constants.RabbitConstants.*;

@Component
@Slf4j
public class RabbitSendService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        if (StringUtils.isEmpty(message)) {
            return;
        }
        try {
            log.info("send Message {}", message);
            rabbitTemplate.convertAndSend(EXANGE_TEST, ROUTINGKEY_TEST, message);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("send Message {} error {}", message, e);
        }
    }

}
