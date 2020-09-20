package com.study.provider.controller;

import com.study.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author yanghao
 * @create 2020-09-12 13:52
 * @Description:
 */
@RestController
public class UserController {

    @GetMapping("/getUserById/{id}")
    public List<User> getUserById(@PathVariable String id){
        String[] strings = id.split(",");
        List<User> list = new ArrayList<>();
        for (String s : strings){
            User user1 = new User();
            user1.setId(Integer.parseInt(s));
            user1.setUsername(UUID.randomUUID().toString());
            user1.setPassword(String.valueOf(new Random().nextInt(10)));
            list.add(user1);
        }
        System.out.println(list);
        return list;
    }
}
