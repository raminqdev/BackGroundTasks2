package com.raminq.abohava.service;

import com.raminq.abohava.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final FileService fileService;
    private final SerializerService serializerService;
    @Value("${filePath}")
    private String filePath;

    @Autowired
    public WeatherService(FileService fileService, SerializerService serializerService) {
        this.fileService = fileService;
        this.serializerService = serializerService;
    }

    public List<Weather> list() {
        String result = fileService.readFromFile(filePath);
        List<Weather> weathers = serializerService.deserializeToListFromJson(result, Weather.class);
        weathers.sort((a, b) -> (int) (a.getTemp() - b.getTemp()));
        return weathers;
    }
}
