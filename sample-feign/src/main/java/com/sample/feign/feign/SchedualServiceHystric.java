package com.sample.feign.feign;

public class SchedualServiceHystric implements BalancedFeign{
    @Override
    public String sayFromClientOne() {
        return "这个服务出问题了";
    }
}
