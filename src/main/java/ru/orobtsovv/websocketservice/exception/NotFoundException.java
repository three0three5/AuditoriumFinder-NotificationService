package ru.orobtsovv.websocketservice.exception;

import static ru.orobtsovv.websocketservice.utils.Constants.NOT_FOUND_EXCEPTION;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super(NOT_FOUND_EXCEPTION);
    }
}
