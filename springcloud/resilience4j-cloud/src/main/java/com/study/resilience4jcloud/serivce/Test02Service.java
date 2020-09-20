package com.study.resilience4jcloud.serivce;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @author yanghao
 * @create 2020-09-13 15:51
 * @Description:
 */
@Service
public class Test02Service {

    @Autowired
    private RestTemplate restTemplate;

    public String rateLimiter(){
        for (int i = 0; i < 5; i++) {
            restTemplate.getForObject("http://provider/rateLimiter", String.class);
        }
        return "success";
    }

}
