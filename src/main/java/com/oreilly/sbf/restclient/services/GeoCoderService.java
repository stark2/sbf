package com.oreilly.sbf.restclient.services;

import com.oreilly.sbf.entities.Site;
import com.oreilly.sbf.json.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GeoCoderService {

    private static final Logger log = LoggerFactory.getLogger(GeoCoderService.class);
    private static final String BASE = "https://maps.googleapis.com/maps/api/geocode/json";

    @Value("${geocoder.key}")
    private String KEY; // = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";

    private WebClient client;

    @Autowired
    public GeoCoderService(WebClient.Builder builder) {
        client = builder.baseUrl("https://maps.googleapis.com").build();
    }

    private String encodeString(String s) {
        try {
            return URLEncoder.encode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public Site getLatLng(String... address) {
        String encoded = Stream.of(address)
                .map(this::encodeString)
                .collect(Collectors.joining(","));
        String path = "/maps/api/geocode/json";
        Response response = client.get()
                .uri(uriBuilder ->
                        uriBuilder.path(path)
                                .queryParam("address", encoded)
                                .queryParam("key", KEY)
                                .build())
                .retrieve()
                .bodyToMono(Response.class)
                .block(Duration.ofSeconds(2));
        return new Site(response.getFormattedAddress(),
                response.getLocation().getLat(),
                response.getLocation().getLng());
    }
}
