package com.example.websocketservice.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;

import static com.example.websocketservice.utils.Constants.USER_DESTINATION_PREFIX;
import static com.example.websocketservice.utils.Constants.USER_ID_HEADER_NAME;

@Slf4j
public class SubscribeInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("presend");
        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(message);
        if (!StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            return message;
        }
        String userId = (String)accessor.getSessionAttributes().get(USER_ID_HEADER_NAME);
        String destination = accessor.getDestination();
        if (userId == null || destination == null) {
            return null;
        }
        String newDestination = USER_DESTINATION_PREFIX + "/" + userId + destination;
        log.info("new destination: " + newDestination);
        accessor.setDestination(newDestination);
        return MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders());
    }
}
