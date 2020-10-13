package com.yulin.rabbitmq.work;

import cn.hutool.core.thread.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "work")
public class WorkReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkReceiver.class);
    private final int instance;

    public WorkReceiver(int instance) {
        this.instance = instance;
    }

    @RabbitHandler
    public void receiver(String in) {
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < in.length(); i++) {
            ThreadUtil.sleep(1000);
        }
        watch.stop();
        LOGGER.info("instance "+this.instance+" [x] Received "+in + " done in "+watch.getTotalTimeSeconds());

    }
}
