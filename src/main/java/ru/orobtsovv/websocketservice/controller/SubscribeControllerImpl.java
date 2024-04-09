package ru.orobtsovv.websocketservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.websocketservice.dto.ShortMessageResponse;
import ru.orobtsovv.websocketservice.service.SubscribeService;

@RestController
@AllArgsConstructor
public class SubscribeControllerImpl implements SubscribeController {
    private SubscribeService service;

    @Override
    public ResponseEntity<ShortMessageResponse> subscribeToNotificationsFromUser(int id, int userid) {
        return ResponseEntity.ok(service.sub(userid, id));
    }

    @Override
    public ResponseEntity<ShortMessageResponse> unsubscribeFromNotificationsFromUser(int id, int userid) {
        return ResponseEntity.ok(service.unsub(userid, id));
    }
}
