package com.xu.wemall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置模板
 *
 * @author like
 */
@Configuration
public class RestTemplateConfig {

    // 配置 RestTemplate
    @Autowired
    RestTemplateBuilder restTemplateBuilider;

    @Bean
    public RestTemplate restTemplate() {
        //使用build()方法进行获取
        return restTemplateBuilider.build();
    }

}

