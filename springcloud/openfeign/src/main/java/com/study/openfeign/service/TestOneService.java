package com.study.openfeign.service;

import com.study.api.service.IUserService;
import com.study.openfeign.fallback.HelloServiceFallback;
import com.study.openfeign.fallback.HelloServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yanghao
 * @create 2020-09-12 20:25
 * @Description:
 */
@FeignClient(value = "provider", fallbackFactory = HelloServiceFallbackFactory.class)
public interface TestOneService extends IUserService {
}
