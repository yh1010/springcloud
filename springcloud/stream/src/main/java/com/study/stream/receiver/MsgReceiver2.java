package com.study.stream.receiver;

import com.study.stream.channel.MyChannel;
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
@EnableBinding(MyChannel.class)
public class MsgReceiver2 {

    public static final Logger logger = LoggerFactory.getLogger(MsgReceiver2.class);

    @StreamListener(MyChannel.INPUT)
    public void receive(Object payload){
        logger.info("Received2: {}", payload);
    }
}
