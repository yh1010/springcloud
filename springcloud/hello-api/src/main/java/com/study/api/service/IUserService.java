package com.study.api.service;

import com.study.model.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * @author yanghao
 * @create 2020-09-12 22:43
 * @Description:
 */
public interface IUserService {

    @GetMapping("/hello") //与provider服务接口相对应
    String hello();

    @GetMapping("/hello1")
    String hello1(@RequestParam("name") String name);

    @GetMapping("/getUserName")
    void getUserName(@RequestHeader("name") String name) throws UnsupportedEncodingException;

    @PostMapping("/addUser")
    User addUser(@RequestBody User user);

    @GetMapping("/rateLimiter")
    String rateLimiter();

}
