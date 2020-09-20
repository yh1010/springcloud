package com.study.openfeign.fallback;

import com.study.model.User;
import com.study.openfeign.service.TestOneService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author yanghao
 * @create 2020-09-13 0:03
 * @Description:
 */
@Component
public class HelloServiceFallbackFactory implements FallbackFactory<TestOneService> {
    @Override
    public TestOneService create(Throwable throwable) {
        return new TestOneService() {
            @Override
            public String hello() {
                return "error----";
            }

            @Override
            public String hello1(String name) {
                return "error1----";
            }

            @Override
            public void getUserName(String name) throws UnsupportedEncodingException {

            }

            @Override
            public User addUser(User user) {
                return null;
            }
        };
    }
}
