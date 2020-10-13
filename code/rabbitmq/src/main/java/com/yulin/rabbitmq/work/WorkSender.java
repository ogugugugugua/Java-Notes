package com.yulin.rabbitmq.work;

import com.yulin.rabbitmq.simple.SimpleSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkSender.class);
    @Autowired
    private RabbitTemplate template;
    private static final String queueName = "work";

    public void send(int times){
        StringBuilder temp = new StringBuilder();
        for (int i = -1; i < times%3; i++) {
            temp.append("a");
        }
        String message = temp.toString();
        template.convertAndSend(queueName, message);
        LOGGER.info(" [x] Sent '{}'", message);
    }
}
