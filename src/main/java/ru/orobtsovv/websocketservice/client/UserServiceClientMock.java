package ru.orobtsovv.websocketservice.client;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("user-mock")
@Primary
public class UserServiceClientMock implements UserServiceClient {
    @Override
    public boolean areFriends(int id1, int id2) {
        return true;
    }
}
