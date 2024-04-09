package ru.orobtsovv.websocketservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.orobtsovv.websocketservice.domain.SubscribeRepository;
import ru.orobtsovv.websocketservice.model.Notification;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final WebsocketSender sender;
    private final SubscribeRepository repository;

    public void updateFrom(int id, Notification<?> update) {
        List<Integer> subscribers = repository.findSubscribers(id);
        update.setFromId(id);
        subscribers.forEach(subId -> {
            update.setToId(subId);
            sender.sendMessageToDestination(update);
            sender.sendMessageToClient(subId, update);
        });
    }

    public void directSend(int sendTo, Notification<?> update) {
        update.setToId(sendTo);
        sender.sendMessageToDestination(update);
        sender.sendMessageToClient(sendTo, update);
    }
}
