package com.study.sleuth.controller;

import com.study.sleuth.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @create 2020-09-20 0:06
 * @Description:
 */
@RestController
public class TestController {

    private static final Log log = LogFactory.getLog(TestController.class);

    @Autowired
    private TestService testService;

    @GetMapping("/hello")
    public String hello(){
        log.info("hello, spring cloud sleuth");
        return "hello, spring cloud sleuth";
    }

    @GetMapping("/hello1")
    public String hello1(){
        String backgroundFun = testService.backgroundFun();
        System.out.println(backgroundFun);
        log.info("hello1");
        return backgroundFun;
    }
}
