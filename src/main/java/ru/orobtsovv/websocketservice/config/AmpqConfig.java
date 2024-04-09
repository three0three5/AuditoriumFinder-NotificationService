package ru.orobtsovv.websocketservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ru.orobtsovv.websocketservice.utils.MQConstants.AUDITORIUM_UPDATE;
import static ru.orobtsovv.websocketservice.utils.MQConstants.FRIEND_LINK_DELETED;
import static ru.orobtsovv.websocketservice.utils.MQConstants.FRIEND_REQUEST_ACCEPTED;
import static ru.orobtsovv.websocketservice.utils.MQConstants.FRIEND_REQUEST_RECEIVED;
import static ru.orobtsovv.websocketservice.utils.MQConstants.PROFILE_DELETE_NOTIFICATIONS;
import static ru.orobtsovv.websocketservice.utils.MQConstants.PROFILE_UPDATE;

@Configuration
public class AmpqConfig {
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue friendLinkDeleteQueue() {
        return new Queue(FRIEND_LINK_DELETED, true);
    }

    @Bean
    public Queue friendRequestReceivedQueue() {
        return new Queue(FRIEND_REQUEST_RECEIVED, true);
    }

    @Bean
    public Queue friendAcceptedQueue() {
        return new Queue(FRIEND_REQUEST_ACCEPTED, true);
    }

    @Bean
    public Queue profileDeleteQueue() {
        return new Queue(PROFILE_DELETE_NOTIFICATIONS, true);
    }

    @Bean
    public Queue profileUpdateQueue() {
        return new Queue(PROFILE_UPDATE, true);
    }

    @Bean
    public Queue auditoriumUpdateQueue() {
        return new Queue(AUDITORIUM_UPDATE, true);
    }
}
