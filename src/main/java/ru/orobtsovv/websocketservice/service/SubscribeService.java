package ru.orobtsovv.websocketservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.websocketservice.client.UserServiceClient;
import ru.orobtsovv.websocketservice.client.UserServiceClientImpl;
import ru.orobtsovv.websocketservice.domain.SubscribeEntity;
import ru.orobtsovv.websocketservice.domain.SubscribeId;
import ru.orobtsovv.websocketservice.domain.SubscribeRepository;
import ru.orobtsovv.websocketservice.dto.ShortMessageResponse;
import ru.orobtsovv.websocketservice.exception.NotFoundException;
import ru.orobtsovv.websocketservice.exception.NotFriendsException;

import static ru.orobtsovv.websocketservice.utils.Constants.SUB_SUCCESS;
import static ru.orobtsovv.websocketservice.utils.Constants.UNSUB_SUCCESS;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository repository;
    private final UserServiceClient client;

    @Transactional
    public ShortMessageResponse sub(int userid, int id) {
        log.info("sub from %d to %d".formatted(userid, id));
        boolean areFriends = client.areFriends(userid, id);
        if (!areFriends) throw new NotFriendsException();
        repository.save(new SubscribeEntity(userid, id));
        return new ShortMessageResponse(SUB_SUCCESS);
    }

    public ShortMessageResponse unsub(int userid, int id) {
        log.info("unsub from %d to %d".formatted(userid, id));
        SubscribeId subscribeId = new SubscribeId(userid, id);
        if (!repository.existsById(subscribeId)) throw new NotFoundException();
        repository.deleteById(new SubscribeId(userid, id));
        return new ShortMessageResponse(UNSUB_SUCCESS);
    }
}
