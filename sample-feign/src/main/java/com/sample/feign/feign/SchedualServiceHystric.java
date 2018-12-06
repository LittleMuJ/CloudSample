package com.sample.feign.feign;

//熔断器
public class SchedualServiceHystric implements BalancedFeign{
    @Override
    public String sayFromClientOne() {
        return "这个服务出问题了";
    }
}
