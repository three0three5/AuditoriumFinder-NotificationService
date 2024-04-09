package ru.orobtsovv.websocketservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Notification<T> {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private NotificationType type;
    private T payload;
    private int fromId;
    private int toId;
}
