package com.lcwd.user.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.config.interceptor.RestTemplateInterceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.client.ClientHttpRequestInterceptor;


@Configuration
public class AppConfig {
    
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    	interceptors.add(new RestTemplateInterceptor());
    	restTemplate.setInterceptors(interceptors);
   //     return new RestTemplate();
    	return restTemplate;
    }
}

