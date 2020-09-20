package com.study.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author yanghao
 * @create 2020-09-12 14:01
 * @Description:
 */
@Service
public class BatchService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * hystrix 请求合并
     * @param id
     * @return
     */
    @HystrixCollapser(batchMethod = "getUserByIds", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "200")
    })
    public Future<User> getUserById(Integer id){
        return null;
    }

    @HystrixCommand
    public List<User> getUserByIds(List<Integer> ids){
        User[] users = restTemplate.getForObject("http://provider/getUserById/{1}", User[].class, StringUtils.join(ids, ","));
        return Arrays.asList(users);
    }
}
