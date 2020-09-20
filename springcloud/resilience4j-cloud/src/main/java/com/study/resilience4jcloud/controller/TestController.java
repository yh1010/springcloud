package com.study.resilience4jcloud.controller;

import com.study.resilience4jcloud.serivce.Test01Service;
import com.study.resilience4jcloud.serivce.Test02Service;
import com.study.resilience4jcloud.serivce.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @create 2020-09-13 15:58
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private Test01Service test01Service;

    @Autowired
    private Test02Service test02Service;

    /**
     * 请求重试
     * @return
     */
    @GetMapping("/test")
    public String test(){
        return testService.hello();
    }

    /**
     * 断路器
     * @return
     */
    @GetMapping("/test1")
    public String test1(){
        return test01Service.hello();
    }

    /**
     * 限流
     * @return
     */
    @GetMapping("/test2")
    public String test2(){
        return test02Service.rateLimiter();
    }
}
