package ru.orobtsovv.websocketservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeId implements Serializable {
    private int from;
    private int to;
}
