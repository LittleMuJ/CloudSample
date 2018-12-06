package com.sample.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//添加熔断器只需添加fallback即可
@FeignClient(value = "user-center",fallback = SchedualServiceHystric.class)
public interface BalancedFeign {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    String sayFromClientOne();
}
