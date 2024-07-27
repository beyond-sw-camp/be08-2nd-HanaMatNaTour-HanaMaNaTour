package com.example.demo.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@RequiredArgsConstructor
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // 웹 소켓을 구성하는 설정 파일을 작성한다.
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        // endpoint 설정 : /api/v1/chat/{postId}
//        // 이를 통해서 ws://localhost:8080/ws/chat 으로 요청이 들어오면 websocket 통신을 진행한다.
//        // setAllowedOrigins("*") 는 모든 ip에서 접속 가능하도록 해줌
//        registry.addHandler(webSocketHandler,"/ws/chat").setAllowedOrigins("*");
//    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }
}
