package com.study.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author yanghao
 * @create 2020-09-19 23:04
 * @Description: 消息通道
 */
public interface MyChannel {

    String INPUT = "suyu-input";
    String OUTPUT = "suyu-output";

    @Output(value = OUTPUT)
    MessageChannel output();

    @Input(value = INPUT)
    SubscribableChannel input();
}
