package ru.orobtsovv.websocketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequestReceivedMessage {
    private int userid;
    private int senderId;
}
