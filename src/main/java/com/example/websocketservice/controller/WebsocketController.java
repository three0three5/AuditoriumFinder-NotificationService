package com.example.websocketservice.controller;

import com.example.websocketservice.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebsocketController {
    private NotificationService notificationService;

    @GetMapping("/trigger/{userid}")
    public void trigger(@PathVariable int userid) {
        notificationService.sendMessageToClient(userid, "Seen only by " + userid);
    }
}
