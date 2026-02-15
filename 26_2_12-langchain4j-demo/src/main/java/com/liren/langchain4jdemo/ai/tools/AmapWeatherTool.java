package com.liren.langchain4jdemo.ai.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AmapWeatherTool {

//    private final WebClient client = WebClient.create("https://restapi.amap.com");

    private final String key;
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public AmapWeatherTool(String key) {
        this.key = key;
    }

//    @Tool("调用高德地图天气 API，根据城市编码或名称返回天气信息")
//    public String getAmapWeather(
//            @P(value = "城市adcode或城市名称") String cityCodeOrName,
//            @P(value = "天气类型: base=实时, all=预报") String extensions
//    ) {
//
//        try {
//            var response = client.get()
//                    .uri(uriBuilder -> uriBuilder
//                            .path("/v3/weather/weatherInfo")
//                            .queryParam("key", amapKey)
//                            .queryParam("city", cityCodeOrName)
//                            .queryParam("extensions", extensions)
//                            .queryParam("output", "JSON")
//                            .build())
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .block();
//
//            return response != null ? response : "高德天气 API 返回为空";
//        } catch (Exception e) {
//            return "调用高德天气 API 失败: " + e.getMessage();
//        }
//    }

    @Tool("调用高德地图天气 API 并返回结构化天气信息")
    public WeatherInfo getWeather(@P(value = "城市名称或行政编码，例如 Beijing 或 110101") String city) {
        try {
            String url = String.format(
                    "https://restapi.amap.com/v3/weather/weatherInfo" +
                            "?key=%s&city=%s&extensions=base&output=JSON",
                    key, city
            );
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode root = mapper.readTree(response.body());
            JsonNode lives = root.path("lives");

            if (!lives.isArray() || lives.size() == 0) {
                throw new RuntimeException("高德天气返回数据为空");
            }

            JsonNode info = lives.get(0);

            return new WeatherInfo(
                    info.path("city").asText(),
                    info.path("weather").asText(),
                    info.path("temperature").asInt(),
                    info.path("winddirection").asText(),
                    info.path("windpower").asText(),
                    info.path("humidity").asInt(),
                    info.path("reporttime").asText()
            );

        } catch (Exception e) {
            throw new RuntimeException("天气API调用失败: " + e.getMessage(), e);
        }
    }
}
