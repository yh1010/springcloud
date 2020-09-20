package com.study.provider.controller;

import com.study.api.service.IUserService;
import com.study.model.User;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @author yanghao
 * @create 2020-09-05 18:26
 * @Description:
 */
@RestController
public class TestController implements IUserService {

    @Override
    public String hello(){
        String s = "hello eureka-provider";
        System.out.println(s);
        int i = 1 / 0;
        return s;
    }

    @Override
    public String hello1(String name){
        return "hello" + name;
    }

    @Override
    public void getUserName(@RequestHeader String name) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode(name, "utf-8"));
    }

    @Override
    public User addUser(@RequestBody User user){
        return user;
    }

    @Override
    @RateLimiter(name = "rlA")
    public String rateLimiter() {
        String s = "hello RateLimiter";
        System.out.println(new Date());
        return s;
    }

}
