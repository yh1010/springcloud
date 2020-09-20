package com.study.stream.controller;

import com.study.stream.channel.MyChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @create 2020-09-19 23:15
 * @Description:
 */
@RestController
public class MsgController {

    @Autowired
    private MyChannel myChannel;

    @GetMapping("/hello")
    public void hello(){
        myChannel.output().send(MessageBuilder.withPayload("hello, cloud stream!").build());
    }
}
