package ru.orobtsovv.websocketservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProfileUpdateMessage {
    private int userid;
    private String oldNickname;
    private String newNickname;
}
