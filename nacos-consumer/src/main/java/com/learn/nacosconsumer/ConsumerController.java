package com.learn.nacosconsumer;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @NacosInjected
    private NamingService namingService;

    @GetMapping("/get")
    public String get(@RequestParam String name) throws NacosException {
        List<Instance> allInstance=namingService.getAllInstances("nacos-producer");
        String ip=allInstance.get(0).getIp();
        int port=allInstance.get(0).getPort();
        return restTemplate.getForObject("http://"+ip+":"+port+"/discovery/get?serviceName=" + name, String.class);
    }
}

