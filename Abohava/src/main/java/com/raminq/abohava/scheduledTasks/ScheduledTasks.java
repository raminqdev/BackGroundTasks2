package com.raminq.abohava.scheduledTasks;

import jdk.nashorn.internal.parser.JSONParser;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
This example uses fixedRate, which specifies the interval between method invocations,
measured from the start time of each invocation. There are other options, such as fixedDelay,
which specifies the interval between invocations measured from the completion of the task.
You can also use @Scheduled(cron=". . .") expressions for more sophisticated task scheduling.
https://spring.io/guides/gs/scheduling-tasks/
 */

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private RestTemplate restTemplate;
    @Value("${openWeatherMap.Api}")
    private String api;
    @Value("${openWeatherMap.AppId}")
    private String appId;

//
//    public ScheduledTasks(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @Bean
    public RestTemplate setTemplate() {
        return new RestTemplate();
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        var response = restTemplate
                .getForObject(api + "?q=tehran,IR&units=metric&appid=" + appId + "&lang=fa", String.class);
        log.info("The time is now {} " + api, dateFormat.format(new Date()));



        log.info(response);
    }
}

