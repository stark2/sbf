package com.oreilly.sbf.restclient.services;

import com.oreilly.sbf.entities.JokeResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service    // @Component classes: @Controller, @Service, @Repository, @Configuration
            // Adding any other type to the app ctx should be done through JavaConfig
public class JokeService {
    private WebClient client;

    public JokeService(WebClient.Builder builder) {
        client = builder.baseUrl("http://api.icndb.com").build();
    }

    public String getJoke(String first, String last) {
        String path = "/jokes/random";
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam("limitTo", "[nerdy]")
                        .queryParam("firstName", first)
                        .queryParam("lastName", last)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(JokeResponse.class)
                .map(jokeResponse -> jokeResponse.getValue().getJoke())
                .block(Duration.ofSeconds(2));
    }

    public String getJokeAsString(String first, String last) {
        String path = "/jokes/random";
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam("limitTo", "[nerdy]")
                        .queryParam("firstName", first)
                        .queryParam("lastName", last)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block(Duration.ofSeconds(2));
    }
}
