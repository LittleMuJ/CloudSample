package com.sample.applicationmerchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

//消费服务B
public class SampleApplicationmerchantApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplicationmerchantApplication.class, args);
    }
}
