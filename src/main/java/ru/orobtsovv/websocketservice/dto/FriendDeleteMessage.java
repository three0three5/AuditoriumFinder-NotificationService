package ru.orobtsovv.websocketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendDeleteMessage {
    private int id1;
    private int id2;
}
