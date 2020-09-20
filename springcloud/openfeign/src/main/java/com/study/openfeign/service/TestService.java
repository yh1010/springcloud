package com.study.openfeign.service;

import com.study.api.service.IUserService;
import com.study.openfeign.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yanghao
 * @create 2020-09-12 20:25
 * @Description:
 */
@FeignClient(value = "provider", fallback = HelloServiceFallback.class)
public interface TestService extends IUserService {
}
