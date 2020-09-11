package com.tree.springcloud.config;

import com.tree.myrule.MySelfRule;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableDiscoveryClient
/**
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class) //配置个人rule
*/
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced
    /**
     * 使得restTemplate具有负载均衡的能力
     */
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
