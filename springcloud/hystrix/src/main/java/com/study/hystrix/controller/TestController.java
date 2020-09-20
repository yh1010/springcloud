package com.study.hystrix.controller;

import com.study.hystrix.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @create 2020-09-08 22:27
 * @Description:
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/hello")
    public String hello(){
        return testService.hello();
    }

    @GetMapping("/hello1")
    public String hello1(){
        return testService.hello();
    }
}
