package com.sample.applicationa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient

//消费服务B
public class SampleApplicationaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplicationaApplication.class, args);
    }
}
