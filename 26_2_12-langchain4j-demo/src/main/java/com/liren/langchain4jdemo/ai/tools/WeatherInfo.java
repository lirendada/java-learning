package com.liren.langchain4jdemo.ai.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherInfo {
    private String city;
    private String weather;
    private int temperature;
    private String windDirection;
    private String windPower;
    private int humidity;
    private String reportTime;
}
