package com.study.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @create 2020-09-20 13:10
 * @Description:
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello Sentinel";
    }
}
