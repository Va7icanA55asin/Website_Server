package com.github.va7icana55asin.website_server.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class ExternalAPIAdapter {

    @Value("${nasa.api-token}")
    private String nasaApiToken;

    private WebClient nasaWebClient;

    @Autowired
    public ExternalAPIAdapter() {
        this.nasaWebClient = WebClient
                .builder()
                    .baseUrl("https://api.nasa.gov")
                .build();
    }

    public Mono<String> getNasaAPOD() {
        return nasaWebClient
                .get()
                .uri("/planetary/apod?api_key={apiKey}&hd={hdBool}", nasaApiToken, "true")
                .retrieve()
                .bodyToMono(Map.class)
                .map(map -> (String)
                        (map.containsKey("hdurl") ? map.get("hdurl") : map.get("url")));
    }
}
