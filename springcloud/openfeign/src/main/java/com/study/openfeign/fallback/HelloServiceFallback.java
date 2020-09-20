package com.study.openfeign.fallback;

import com.study.model.User;
import com.study.openfeign.service.TestService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

/**
 * @author yanghao
 * @create 2020-09-12 23:59
 * @Description:
 */
@Component
@RequestMapping("/fallback") // 防止请求地址重复
public class HelloServiceFallback implements TestService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello1(String name) {
        return "error1";
    }

    @Override
    public void getUserName(String name) throws UnsupportedEncodingException {

    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public String rateLimiter() {
        return null;
    }
}
