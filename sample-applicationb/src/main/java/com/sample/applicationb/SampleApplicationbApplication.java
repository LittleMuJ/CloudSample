package com.sample.applicationb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

//消费服务B
public class SampleApplicationbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplicationbApplication.class, args);
    }
}
