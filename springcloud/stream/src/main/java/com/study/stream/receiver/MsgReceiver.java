package com.study.stream.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author yanghao
 * @create 2020-09-19 22:43
 * @Description:
 */
@EnableBinding(Sink.class) // 绑定Sink消息通道
public class MsgReceiver {

    public static final Logger logger = LoggerFactory.getLogger(MsgReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload){
        logger.info("Received: {}", payload);
    }
}
