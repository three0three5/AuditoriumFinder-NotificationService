package ru.orobtsovv.websocketservice.model;

import lombok.Data;

@Data
public class Auditorium{
    private int id;
    private String name;
    private int capacity;
    private int socketsAmount;
    private boolean projector;
    private String type;
    private Corpus corpus;
    private Building building;
}
