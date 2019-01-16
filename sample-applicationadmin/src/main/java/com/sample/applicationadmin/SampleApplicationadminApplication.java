package com.sample.applicationadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
//消费服务A
public class SampleApplicationadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplicationadminApplication.class, args);
    }
}
