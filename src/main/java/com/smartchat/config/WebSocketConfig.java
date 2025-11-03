package com.smartchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 최초 연결하는 엔드포인트
        registry.addEndpoint("/ws/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS(); // SockJS fallback 활성화
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 서버 → 클라이언트로 브로드캐스트할 경로 prefix
        registry.enableSimpleBroker("/topic", "/queue");
        // 클라이언트 → 서버로 보낼 때 붙이는 app prefix
        registry.setApplicationDestinationPrefixes("/app");
        // (선택) 사용자 개별 큐 프리픽스
        registry.setUserDestinationPrefix("/user");
    }
}
