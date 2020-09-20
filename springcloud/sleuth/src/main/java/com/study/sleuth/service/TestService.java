package com.study.sleuth.service;

import com.study.sleuth.controller.TestController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author yanghao
 * @create 2020-09-20 0:13
 * @Description:
 */
@Service
public class TestService {

    private static final Log log = LogFactory.getLog(TestService.class);

    @Async
    public String backgroundFun(){
        log.info("backgroundFun");
        return "backgroundFun";
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void backgroundFun1(){
        log.info("start:");
        backgroundFun();
        log.info("end:");
    }
}
