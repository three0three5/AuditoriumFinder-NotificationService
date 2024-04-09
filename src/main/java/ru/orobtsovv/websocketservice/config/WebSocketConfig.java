package ru.orobtsovv.websocketservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import ru.orobtsovv.websocketservice.interceptor.IdentityInterceptor;
import ru.orobtsovv.websocketservice.interceptor.SubscribeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import ru.orobtsovv.websocketservice.interceptor.TelegramInterceptor;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker( "/user");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        IdentityInterceptor interceptor = new IdentityInterceptor();
        TelegramInterceptor tg = new TelegramInterceptor();
        registry.addEndpoint("/ws")
                .addInterceptors(interceptor)
                .setAllowedOrigins("*");
        registry.addEndpoint("/tg/ws")
                .addInterceptors(tg)
                .setAllowedOrigins("*");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new SubscribeInterceptor());
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setSendTimeLimit(15 * 1000) // 15 seconds
                .setSendBufferSizeLimit(512 * 1024) // 512KB
                .setTimeToFirstMessage(10 * 1000); // 10 seconds
    }
}

