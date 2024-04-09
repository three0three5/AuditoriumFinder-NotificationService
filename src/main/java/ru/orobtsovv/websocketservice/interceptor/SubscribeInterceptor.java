package ru.orobtsovv.websocketservice.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import ru.orobtsovv.websocketservice.utils.Constants;

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
        String destination = accessor.getDestination();
        String newDestination;
        if (Constants.USER_DESTINATION.equals(destination)) {
            String userId = (String)accessor.getSessionAttributes().get(Constants.USER_ID_HEADER_NAME);
            if (userId == null) {
                return null;
            }
            newDestination = Constants.USER_DESTINATION_PREFIX + "/" + userId + destination;
        } else if (Constants.TELEGRAM_DESTINATION.equals(destination)) {
            boolean isTgService = (Boolean)accessor.getSessionAttributes().get(Constants.IS_TG_ATTRIBUTE);
            if (!isTgService) {
                return null;
            }
            newDestination = destination;
        } else {
            return null;
        }
        log.info("new destination: " + newDestination);
        accessor.setDestination(newDestination);
        return MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders());
    }
}
