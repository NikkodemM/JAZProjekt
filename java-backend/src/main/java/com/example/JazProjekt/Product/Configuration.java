package com.example.JazProjekt.Product;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class Configuration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
