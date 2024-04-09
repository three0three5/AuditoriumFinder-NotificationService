package ru.orobtsovv.websocketservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.websocketservice.domain.SubscribeRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteService {
    private final SubscribeRepository repository;

    @Transactional
    public void deleteAllFor(int userid) {
        repository.deleteAllByFromOrTo(userid, userid);
    }
}
