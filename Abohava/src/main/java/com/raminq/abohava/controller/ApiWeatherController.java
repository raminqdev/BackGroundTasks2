package com.raminq.abohava.controller;

import com.raminq.abohava.model.Weather;
import com.raminq.abohava.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/weather")
@Api(value = "WeatherControllerApi", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiWeatherController {

    private WeatherService weatherService;

    public ApiWeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(path = "/list")
    @ApiOperation(value = "Gets cities weather")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok")})
    public List<Weather> list() {
        return weatherService.list();
    }
}

