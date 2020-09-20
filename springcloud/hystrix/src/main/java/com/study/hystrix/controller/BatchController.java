package com.study.hystrix.controller;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.study.hystrix.service.BatchService;
import com.study.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author yanghao
 * @create 2020-09-12 14:12
 * @Description:
 */
@RestController
public class BatchController {

    @Autowired
    private BatchService batchService;

    @GetMapping("/getUserById")
    public void getUserById() throws ExecutionException, InterruptedException {
        HystrixRequestContext requestContext = HystrixRequestContext.initializeContext();
        Future<User> future1 = batchService.getUserById(99);
        Future<User> future2 = batchService.getUserById(98);
        Future<User> future3 = batchService.getUserById(97);
        User user = future1.get();
        User user1 = future2.get();
        User user2 = future3.get();
        System.out.println(user);
        System.out.println(user1);
        System.out.println(user2);
        Thread.sleep(10000);
        Future<User> future4 = batchService.getUserById(96);
        User user3 = future4.get();
        System.out.println(user3);
        requestContext.close();
    }
}
