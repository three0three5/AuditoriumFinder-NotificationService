package ru.orobtsovv.websocketservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static ru.orobtsovv.websocketservice.utils.Constants.TELEGRAM_DESTINATION;
import static ru.orobtsovv.websocketservice.utils.Constants.USER_DESTINATION;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebsocketSender {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendMessageToClient(int userId, Object payload) {
        messagingTemplate.convertAndSendToUser(String.valueOf(userId), USER_DESTINATION, payload);
    }

    public void sendMessageToDestination(Object payload) {
        messagingTemplate.convertAndSend(TELEGRAM_DESTINATION, payload);
    }
}
