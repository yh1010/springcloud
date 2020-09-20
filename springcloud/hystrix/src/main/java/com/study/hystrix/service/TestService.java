package com.study.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yanghao
 * @create 2020-09-08 22:27
 * @Description:
 */
@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 1. hystrix服务降级
     * 我们在这个方法上添加 @HystrixCommand 注解，
     * 配置 fallbackMethod 属性，这个属性表示该方法调用失败时的临时替代方法
     * @return
     */
    @HystrixCommand(fallbackMethod = "error")
    public String hello(){
        return restTemplate.getForObject("http://provider/hello", String.class);
    }

    /**
     * 1.1 异常处理
     * @return
     */
    @HystrixCommand(fallbackMethod = "error1")
    public String hello1(){
        int i = 1 / 0;
        return restTemplate.getForObject("http://provider/hello", String.class);
    }

    /**
     * 这个方法名字要和 fallbackMethod 一致
     * 方法返回值也要和对应的方法一致
     *  @return
     * @return
     */
    public String error(){
        return "error";
    }

    /**
     * 返回异常信息
     * @param throwable
     * @return
     */
    public String error1(Throwable throwable){
        return "error" + throwable.getMessage();
    }
}
