package com.study.consulconsumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yanghao
 * @create 2020-09-07 22:30
 * @Description:
 */
@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public void hello(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("consul-provider");
        logger.info("服务地址：{}", serviceInstance.getUri());
        logger.info("服务名称：{}", serviceInstance.getServiceId());
        String s = restTemplate.getForObject(serviceInstance.getUri() + "/hello", String.class);
        logger.info("s: {}", s);
    }
}
