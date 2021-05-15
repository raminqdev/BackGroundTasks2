package com.raminq.abohava.scheduledTasks;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.raminq.abohava.config.ConfigModel;
import com.raminq.abohava.model.Weather;
import com.raminq.abohava.service.FileService;
import com.raminq.abohava.service.SerializerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private final FileService fileService;
    private final SerializerService serializerService;
    private final RestTemplate restTemplate;
    private final ConfigModel configModel;

    public ScheduledTasks(FileService fileService,
                          SerializerService serializerService,
                          RestTemplate restTemplate,
                          ConfigModel configModel) {
        this.fileService = fileService;
        this.serializerService = serializerService;
        this.restTemplate = restTemplate;
        this.configModel = configModel;
    }

    @Scheduled(initialDelay = 1000000, fixedRate = 50000000)
    public void reportCurrentTime() {
        List<Weather> weathers = new ArrayList<>();

        getCities().parallelStream().forEach(city -> {

            String response = getWeatherResponse(city);

            log.info("##### " + Thread.currentThread().getName() + "  " + city);

            Weather weather = getWeather(response);
            weathers.add(weather);
        });

        String json = serializerService.serializeToJson(weathers);
        fileService.writeToFile(json, configModel.getFilePath());
    }

    private String getWeatherResponse(String city) {
        return restTemplate.getForObject(configModel.getApi() + "?q=" +
                city + ",IR&units=metric&appid=" + configModel.getAppId() + "&lang=fa", String.class);
    }

    private Weather getWeather(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        Weather weather = new Weather();
        weather.setName(jsonObject.get("name").getAsString());
        weather.setTemp(jsonObject.get("main").getAsJsonObject().get("temp").getAsDouble());
        weather.setDescription(jsonObject.get("weather").getAsJsonArray().get(0)
                .getAsJsonObject().get("description").getAsString());

        return weather;
    }

    private List<String> getCities() {
        return Arrays.asList(
                "Tehran",
                "Mashhad",
                "Isfahan",
                "Karaj",
                "Shiraz",
                "Tabriz",
                "Qom",
                "Ahvaz",
                "Kermanshah",
                "Urmia",
                "Rasht",
                "Zahedan",
                "Hamadan",
                "Kerman",
                "Yazd",
                "Ardabil",
                "Bandar Abbas",
                "Arak",
                "Sari"
        );
    }
}

