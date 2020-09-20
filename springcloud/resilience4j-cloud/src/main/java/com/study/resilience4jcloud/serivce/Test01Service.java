package com.study.resilience4jcloud.serivce;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yanghao
 * @create 2020-09-13 15:51
 * @Description:
 */
@Service
@CircuitBreaker(name = "cbA", fallbackMethod = "error")
public class Test01Service {

    @Autowired
    private RestTemplate restTemplate;

    public String hello(){
        return restTemplate.getForObject("http://provider/hello", String.class);
    }

    public String error(Throwable throwable){
        return "error";
    }
}
