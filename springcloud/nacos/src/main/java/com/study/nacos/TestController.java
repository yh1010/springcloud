package com.study.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @create 2020-09-20 12:01
 * @Description:
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${name}")
    String name;

    @GetMapping
    public String hello(){
        return name;
    }
}
