package com.raminq.abohava.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Value("${openWeatherMap.Api}")
    private String api;
    @Value("${openWeatherMap.AppId}")
    private String appId;
    @Value("${filePath}")
    private String filePath;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ConfigModel configModel() {
        ConfigModel configModel = new ConfigModel();
        configModel.setApi(api);
        configModel.setAppId(appId);
        configModel.setFilePath(filePath);
        return configModel;
    }
}
