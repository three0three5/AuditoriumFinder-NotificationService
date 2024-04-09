package ru.orobtsovv.websocketservice.exception;

import static ru.orobtsovv.websocketservice.utils.Constants.NOT_FRIENDS_EXCEPTION;

public class NotFriendsException extends RuntimeException {
    public NotFriendsException() {
        super(NOT_FRIENDS_EXCEPTION);
    }
}
