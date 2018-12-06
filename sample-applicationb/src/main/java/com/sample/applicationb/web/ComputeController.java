package com.sample.applicationb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeController {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add( Integer a, Integer b)
    {
        /*System.out.println("host:"+client.getLocalServiceInstance().getHost()+"   -----port:"+client.getLocalServiceInstance().getPort());*/
        return a+b+" ------      host:8999";
    }

}
