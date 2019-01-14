package com.sample.applicationadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

//消费服务A
public class SampleApplicationadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplicationadminApplication.class, args);
    }
}
