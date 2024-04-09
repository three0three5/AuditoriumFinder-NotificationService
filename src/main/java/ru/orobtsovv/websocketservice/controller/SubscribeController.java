package ru.orobtsovv.websocketservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.orobtsovv.websocketservice.dto.ShortMessageResponse;

@Tag(name = "Subscribe API")
public interface SubscribeController {
    @Operation(summary = "Подписка на обновления пользователя с указанным id",
            description = "Если не является другом - ничего не происходит")
    @PostMapping("/{id}/subscribe")
    ResponseEntity<ShortMessageResponse> subscribeToNotificationsFromUser(
            @PathVariable int id,
            @RequestHeader int userid);

    @Operation(summary = "Отмена подписки на обновления пользователя с указанным id")
    @PostMapping("/{id}/unsubscribe")
    ResponseEntity<ShortMessageResponse> unsubscribeFromNotificationsFromUser(
            @PathVariable int id,
            @RequestHeader int userid);
}
