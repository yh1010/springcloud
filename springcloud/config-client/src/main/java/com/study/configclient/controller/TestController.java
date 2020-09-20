package com.study.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @create 2020-09-19 14:21
 * @Description:
 */
@RestController
public class TestController {

    @Value("${suyu}")
    String suYu;

    @GetMapping("/test")
    public String hello(){
        return suYu;
    }
}
