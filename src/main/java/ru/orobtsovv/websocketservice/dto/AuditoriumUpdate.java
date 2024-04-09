package ru.orobtsovv.websocketservice.dto;

import lombok.Data;
import ru.orobtsovv.websocketservice.model.AudUser;
import ru.orobtsovv.websocketservice.model.Auditorium;

@Data
public class AuditoriumUpdate {
    private Auditorium auditorium;
    private AudUser user;
}
