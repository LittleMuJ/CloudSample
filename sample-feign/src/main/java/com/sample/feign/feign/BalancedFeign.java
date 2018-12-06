package com.sample.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user-center",fallback = SchedualServiceHystric.class)
public interface BalancedFeign {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    String sayFromClientOne();
}
