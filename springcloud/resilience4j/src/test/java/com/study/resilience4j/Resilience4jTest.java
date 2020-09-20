package com.study.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;

/**
 * @author yanghao
 * @create 2020-09-13 12:01
 * @Description:
 */
public class Resilience4jTest {

    /**
     * 正常执行
     */
    @Test
    public void test1(){
        //获取一个CircuitBreakerRegistry实例，
        // 可以调用ofDefaults获取一个CircuitBreakerRegistry实例，也可以自定义属性。
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                // 故障率阈值百分比，超过这个阈值，断路器就会打开
                .failureRateThreshold(50)
                // 断路器保持打开的时间，在到达设置的时间之后，断路器会进入到 half open（半打开） 状态
                .waitDurationInOpenState(Duration.ofMillis(1000))
                // 当断路器处于half open状态时，设置环形缓冲区的大小
                .ringBufferSizeInHalfOpenState(2)
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry breakerRegistry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = breakerRegistry.circuitBreaker("苏彧");
        CircuitBreaker circuitBreaker1 = breakerRegistry.circuitBreaker("苏彧", config);
        CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "hello resilience4j");
        Try<String> result = Try.of(supplier).map(v -> v + "hello world");

        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }


    /**
     * 出现异常的断路器
     */
    @Test
    public void test2(){
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                // 故障率阈值百分比，超过这个阈值，断路器就会打开
                .failureRateThreshold(50)
                // 断路器保持打开的时间，在到达设置的时间之后，断路器会进入到 half open（半打开） 状态
                .waitDurationInOpenState(Duration.ofMillis(1000))
                // 当断路器处于half open状态时，设置环形缓冲区的大小
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry breakerRegistry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = breakerRegistry.circuitBreaker("苏彧");
        System.out.println(circuitBreaker.getState()); // 获取断路器的一个状态
        circuitBreaker.onError(0, new RuntimeException());
        System.out.println(circuitBreaker.getState()); // 获取断路器的一个状态
        circuitBreaker.onError(0, new RuntimeException());
        System.out.println(circuitBreaker.getState()); // 获取断路器的一个状态
        CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "hello resilience4j");
        Try<String> result = Try.of(supplier).map(v -> v + "hello world");

        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    /**
     * 限流
     */
    @Test
    public void test3(){
        RateLimiterConfig rateLimiterConfig = RateLimiterConfig.custom()
                // 阈值刷新时间
                .limitRefreshPeriod(Duration.ofMillis(1000))
                // 阈值刷新的频次
                .limitForPeriod(4)
                // 限流之后的冷却时间
                .timeoutDuration(Duration.ofMillis(1000))
                .build();
        RateLimiter limiter = RateLimiter.of("苏彧", rateLimiterConfig);
        CheckedRunnable checkedRunnable = RateLimiter.decorateCheckedRunnable(limiter, () -> {
            System.out.println(new Date());
        });
        Try.run(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .onFailure(t -> System.out.println(t.getMessage()));
    }

    /**
     * 请求重试
     */
    @Test
    public void test4() {
        RetryConfig config = RetryConfig.custom()
                // 请求重试次数
                .maxAttempts(4)
                // 重试间隔
                .waitDuration(Duration.ofMillis(500))
                // 重试异常
                .retryExceptions(RuntimeException.class)
                .build();
        Retry retry = Retry.of("苏彧", config);
        Retry.decorateRunnable(retry, new Runnable() {
           int count = 0;
           // 开启重试功能之后， run方法执行时，如果抛出异常，会自动触发重试功能
            @Override
            public void run() {
                if (count++ < 3){
                    throw new RuntimeException();
                }
            }
        }).run();
    }
}
