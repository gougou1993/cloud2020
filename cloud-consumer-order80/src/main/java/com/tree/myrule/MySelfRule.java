package com.tree.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 不能在@SpringBootApplication扫描下
 */
@Configuration
public class MySelfRule{

    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
