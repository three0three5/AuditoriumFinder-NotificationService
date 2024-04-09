package ru.orobtsovv.websocketservice.client;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
@Setter
@Slf4j
public class UserServiceClientImpl implements UserServiceClient {
    private final WebClient webClient;
    @Value("${user-service.are-friends-endpoint}")
    private String endpoint;

    public boolean areFriends(int userid, int id) {
        log.info("sending request to userservice: %d %d".formatted(userid, id));
        URI uri = UriComponentsBuilder.fromUriString(endpoint)
                .queryParam("id1", userid)
                .queryParam("id2", id)
                .build()
                .toUri();
        return Boolean.TRUE.equals(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }
}
