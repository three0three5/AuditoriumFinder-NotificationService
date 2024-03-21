package com.example.websocketservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendMessageToClient(int userId, Object payload) {
        messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/updates", payload);
    }
}
