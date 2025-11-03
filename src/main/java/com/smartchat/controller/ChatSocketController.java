package com.smartchat.controller;

import com.smartchat.dto.ChatMessage;
import com.smartchat.dto.ChatLogDto;
import com.smartchat.service.ChatLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatLogService logService;

    @MessageMapping("/chat.send")
    public void handleChat(@Payload ChatMessage msg) {
        if (msg.getTimestamp() == 0)
            msg.setTimestamp(System.currentTimeMillis());

        // 1️⃣ DB 저장
        ChatLogDto log = ChatLogDto.builder()
                .sender(msg.getSender())
                .message(msg.getContent())
                .type("CHAT")
                .timestamp(LocalDateTime.now())
                .build();
        logService.save(log);

        // 2️⃣ 브로드캐스트
        messagingTemplate.convertAndSend("/topic/chat", msg);
    }

    @MessageMapping("/chat.join")
    public void handleJoin(@Payload ChatMessage msg) {
        msg.setType(ChatMessage.Type.JOIN);
        msg.setTimestamp(System.currentTimeMillis());
        logService.save(ChatLogDto.builder()
                .sender(msg.getSender())
                .message("JOIN")
                .type("JOIN")
                .timestamp(LocalDateTime.now())
                .build());
        messagingTemplate.convertAndSend("/topic/chat", msg);
    }

    @MessageMapping("/chat.leave")
    public void handleLeave(@Payload ChatMessage msg) {
        msg.setType(ChatMessage.Type.LEAVE);
        msg.setTimestamp(System.currentTimeMillis());
        logService.save(ChatLogDto.builder()
                .sender(msg.getSender())
                .message("LEAVE")
                .type("LEAVE")
                .timestamp(LocalDateTime.now())
                .build());
        messagingTemplate.convertAndSend("/topic/chat", msg);
    }
}
