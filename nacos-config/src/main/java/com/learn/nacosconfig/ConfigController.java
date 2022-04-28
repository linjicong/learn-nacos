package com.learn.nacosconfig;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("config")
public class ConfigController {

    @NacosValue(value = "${name}",autoRefreshed = true)
    private String useLocalCache;

    @GetMapping("/get")
    @ResponseBody
    public String get() {
        return useLocalCache;
    }
}
