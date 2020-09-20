package com.study.openfeign.controller;

import com.study.model.User;
import com.study.openfeign.service.TestOneService;
import com.study.openfeign.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

/**
 * @author yanghao
 * @create 2020-09-12 20:27
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TestOneService testOneService;

    @GetMapping("/hello")
    public String hello() throws UnsupportedEncodingException {
        String hello1 = testService.hello1("苏彧");
        System.out.println("hello1=" + hello1);

        testService.getUserName(URLEncoder.encode("苏彧", "UTF-8"));

        User user = User.builder().id(new Random().nextInt(10))
                .username("苏彧")
                .password("123456")
                .build();
        testService.addUser(user);
        System.out.println("user: " + user);
        return testService.hello();
    }

    @GetMapping("/hello1")
    public String hello1() throws UnsupportedEncodingException {
        String hello1 = testOneService.hello1("苏彧");
        System.out.println("hello1=" + hello1);

        testOneService.getUserName(URLEncoder.encode("苏彧", "UTF-8"));

        User user = User.builder().id(new Random().nextInt(10))
                .username("苏彧")
                .password("123456")
                .build();
        testOneService.addUser(user);
        System.out.println("user: " + user);
        return testOneService.hello();
    }
}
