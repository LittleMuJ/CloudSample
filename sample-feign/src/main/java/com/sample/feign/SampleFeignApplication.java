package com.sample.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
//声明它是一个资源服务端，即可以通过某些接口调用一些资源
@EnableDiscoveryClient
//声明他是个负载均衡服务
@EnableFeignClients

/*负载均衡启动类*/
public class SampleFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleFeignApplication.class, args);
    }
}
