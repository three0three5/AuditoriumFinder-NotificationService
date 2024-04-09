package ru.orobtsovv.websocketservice.mqhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.websocketservice.dto.AuditoriumUpdate;
import ru.orobtsovv.websocketservice.dto.FriendRequestAcceptedMessage;
import ru.orobtsovv.websocketservice.dto.FriendRequestReceivedMessage;
import ru.orobtsovv.websocketservice.dto.ProfileUpdateMessage;
import ru.orobtsovv.websocketservice.model.Notification;
import ru.orobtsovv.websocketservice.model.NotificationType;
import ru.orobtsovv.websocketservice.service.NotificationService;

import static ru.orobtsovv.websocketservice.utils.MQConstants.AUDITORIUM_UPDATE;
import static ru.orobtsovv.websocketservice.utils.MQConstants.FRIEND_REQUEST_ACCEPTED;
import static ru.orobtsovv.websocketservice.utils.MQConstants.FRIEND_REQUEST_RECEIVED;
import static ru.orobtsovv.websocketservice.utils.MQConstants.PROFILE_UPDATE;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdatesListener {
    private final NotificationService service;

    @Transactional
    @RabbitListener(queues = PROFILE_UPDATE)
    public void onUserUpdate(ProfileUpdateMessage message) {
        log.info("On user update: %s".formatted(message));
        Notification<ProfileUpdateMessage> notification = new Notification<ProfileUpdateMessage>()
                .setType(NotificationType.FRIEND_NICKNAME)
                .setPayload(message);
        service.updateFrom(message.getUserid(), notification);
    }

    @Transactional
    @RabbitListener(queues = AUDITORIUM_UPDATE)
    public void onAuditoriumUpdate(AuditoriumUpdate update) {
        log.info("On auditorium update: %s".formatted(update));
        Notification<AuditoriumUpdate> notification = new Notification<>();
        notification.setType(NotificationType.AUDITORIUM);
        notification.setPayload(update);
        service.updateFrom(update.getUser().getId(), notification);
    }

    @Transactional
    @RabbitListener(queues = FRIEND_REQUEST_ACCEPTED)
    public void onFriendRequestAccept(FriendRequestAcceptedMessage message) {
        log.info("on friend request accepted: %s".formatted(message));
        Notification<FriendRequestAcceptedMessage> notification = new Notification<>();
        notification.setType(NotificationType.FRIEND_REQUEST_ACCEPTED);
        notification.setPayload(message);
        notification.setFromId(message.getAcceptedId());
        notification.setToId(message.getUserid());
        service.directSend(message.getUserid(), notification);
    }

    @Transactional
    @RabbitListener(queues = FRIEND_REQUEST_RECEIVED)
    public void onFriendRequestSent(FriendRequestReceivedMessage message) {
        log.info("on friend request sent: %s".formatted(message));
        Notification<FriendRequestReceivedMessage> notification = new Notification<>();
        notification.setType(NotificationType.FRIEND_REQUEST_INCOMING);
        notification.setPayload(message);
        notification.setToId(message.getUserid());
        notification.setFromId(message.getSenderId());
        service.directSend(message.getUserid(), notification);
    }
}
