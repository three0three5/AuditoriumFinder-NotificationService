package ru.orobtsovv.websocketservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.orobtsovv.websocketservice.dto.ShortMessageResponse;
import ru.orobtsovv.websocketservice.exception.NotFoundException;
import ru.orobtsovv.websocketservice.exception.NotFriendsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFriendsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ShortMessageResponse handleNotFriends(NotFriendsException e) {
        return new ShortMessageResponse(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ShortMessageResponse handleNotFound(NotFoundException e) {
        return new ShortMessageResponse(e.getMessage());
    }
}
