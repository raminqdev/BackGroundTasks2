package com.raminq.abohava.controller;

import com.raminq.abohava.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/weather/list")
    public String list(Model model) {
        model.addAttribute("cities", weatherService.list());
        return "weathers";
    }
}
