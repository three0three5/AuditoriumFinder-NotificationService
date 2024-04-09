package ru.orobtsovv.websocketservice.model;

import lombok.Data;

@Data
public class AudUser {
    private int id;
    private boolean silentStatus;
    private String end;
}
