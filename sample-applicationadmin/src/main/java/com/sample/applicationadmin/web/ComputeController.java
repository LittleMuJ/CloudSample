package com.sample.applicationadmin.web;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ComputeController {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add( Integer a,  Integer b)
    {
        List<ServiceInstance> serverA = client.getInstances("service-A");
        ServiceInstance serviceInstance=serverA.get(0);
        serviceInstance.getHost();
        serviceInstance.getPort();
        /*System.out.println("host:"+client.getLocalServiceInstance().getHost()+"   -----port:"+client.getLocalServiceInstance().getPort());*/
        System.out.println(22222);
        return a+b+" ------      host:"+serviceInstance.getHost()+"   -----port:"+serviceInstance.getPort();
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String loginhtml( )
    {
        return "login";
    }

    @RequestMapping(value = "/index.html",method = RequestMethod.GET)
    public String indexhtml( )
    {
        return "index";
    }

    @RequestMapping(value = "/main.html",method = RequestMethod.GET)
    public String mainhtml( )
    {
        return "sys/main";
    }
}
