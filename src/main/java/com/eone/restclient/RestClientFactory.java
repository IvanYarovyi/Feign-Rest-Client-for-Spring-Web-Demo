package com.eone.restclient;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;

public class RestClientFactory {

    public static <T> T clientOf(Class<T> api, String url) {
        T target =
                Feign.builder()
                        .contract(new SpringMvcContract())
                        .decoder(new JacksonDecoder())
                        .encoder(new JacksonEncoder())
                        .requestInterceptor(template ->
                                template.header("Content-Type", "application/json"))
                        .target(api, url);
        return target;
    }
}
