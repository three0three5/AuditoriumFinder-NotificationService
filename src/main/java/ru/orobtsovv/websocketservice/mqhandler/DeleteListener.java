package ru.orobtsovv.websocketservice.mqhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.websocketservice.dto.FriendDeleteMessage;
import ru.orobtsovv.websocketservice.dto.ProfileDeleteMessage;
import ru.orobtsovv.websocketservice.service.DeleteService;
import ru.orobtsovv.websocketservice.service.SubscribeService;

import static ru.orobtsovv.websocketservice.utils.MQConstants.FRIEND_LINK_DELETED;
import static ru.orobtsovv.websocketservice.utils.MQConstants.PROFILE_DELETE_NOTIFICATIONS;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeleteListener {
    private final SubscribeService service;
    private final DeleteService deleteService;

    @Transactional
    @RabbitListener(queues = PROFILE_DELETE_NOTIFICATIONS)
    public void onProfileDelete(ProfileDeleteMessage message) {
        log.info("on profile delete: %s".formatted(message));
        deleteService.deleteAllFor(message.getUserid());
    }

    @Transactional
    @RabbitListener(queues = FRIEND_LINK_DELETED)
    public void onFriendDelete(FriendDeleteMessage message) {
        log.info("on friend link delete: %s".formatted(message));
        service.unsub(message.getId1(), message.getId2());
        service.unsub(message.getId2(), message.getId1());
    }
}
