package com.study.consumer.controller;

import com.study.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.*;

/**
 * @author yanghao
 * @create 2020-09-05 18:29
 * @Description:
 */
@RestController
@Slf4j
public class TestController {

    /**
     * 服务发现
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    @Qualifier("restTemplateOne")
    private RestTemplate restTemplateOne;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    /**
     * HttpURLConnection访问
     * @return
     */
    @GetMapping("/hello1")
    public String hello1(){
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:9003/hello");
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == 200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                return readLine;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }


    /**
     * DiscoveryClient改造后
     * @return
     */
    @GetMapping("/hello2")
    public String hello2(){

        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        ServiceInstance instance = provider.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/hello");
        HttpURLConnection conn = null;
        try {
            URL url = new URL(stringBuffer.toString());
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == 200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                return readLine;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }


    /**
     * RestTemplate改造后
     * @return
     */
    @GetMapping("/hello3")
    public String hello3(){

        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        ServiceInstance instance = provider.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/hello");
        String s = restTemplateOne.getForObject(stringBuffer.toString(), String.class);
        return s;
    }


    /**
     * 负载均衡Ribbon改造后
     * @return
     */
    @GetMapping("/hello4")
    public String hello4(){
        return restTemplate.getForObject("http://provider/hello", String.class);
    }


    /**
     * getForObject和getForEntity
     */
    @GetMapping("/hello5")
    public void hello5(){
        String string = "java";
        String forObject = restTemplate.getForObject("http://provider/hello1?name={1}", String.class, string);
        log.info("getForObject: {}", forObject);

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://provider/hello1?name={1}", String.class, string);
        String body = forEntity.getBody();
        log.info("body: {}", body);
        HttpStatus statusCode = forEntity.getStatusCode();
        log.info("statusCode: {}", statusCode);
        int codeValue = forEntity.getStatusCodeValue();
        log.info("codeValue: {}", codeValue);
        HttpHeaders headers = forEntity.getHeaders();
        headers.forEach((key, value) -> System.out.println(key + ":" + value));
    }


    /**
     * getForObject三种传参方式
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/hello6")
    public void hello6() throws UnsupportedEncodingException {
        // 第一种传参方式
        String string = "java";
        String forObject = restTemplate.getForObject("http://provider/hello1?name={1}", String.class, string);
        log.info("getForObject: {}", forObject);

        // 第二种传参方式
        Map<String, Object> map = new HashMap();
        map.put("name", string);
        String forObject1 = restTemplate.getForObject("http://provider/hello1?name={name}", String.class, map);
        log.info("getForObject1: {}", forObject1);

        // 第三种传参方式：传递中文字符
        String url = "http://provider/hello1?name=" + URLEncoder.encode(string, "UTF-8");
        URI uri = URI.create(url);
        String forObject2 = restTemplate.getForObject(uri, String.class);
        log.info("getForObject2: {}", forObject2);
    }


    /**
     * post请求传递参数的两种方式
     */
    @GetMapping("/hello7")
    public void hello7(){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("id", 99);
        map.add("username", "java");
        map.add("password", "123456");
        User user = restTemplate.postForObject("http://provider/addUser", map, User.class);
        log.info("user: {}", user.toString());

        User user1 = User.builder()
                .id(100)
                .username("cloud")
                .password("1111")
                .build();
        User user2 = restTemplate.postForObject("http://provider/addUser1", user1, User.class);
        log.info("user2: {}", user2.toString());
    }


    /**
     * postForLocation
     * @Description:
     * 有的时候，当我执行完一个 post 请求之后，立马要进行重定向，
     * 一个非常常见的场景就是注册，注册是一个 post 请求，注册完成之后，
     * 立马重定向到登录页面去登录。对于这种场景，我们就可以使用 postForLocation
     */
    @GetMapping("/hello8")
    public void hello8(){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(16);
        map.add("id", 99);
        map.add("username", "java");
        map.add("password", "123456");

        URI uri = restTemplate.postForLocation("http://provider/register", map);
        String s = restTemplate.getForObject(uri, String.class);
        log.info("s: {}", s);
    }

}
