package com.smartchat.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatMessage {
    public enum Type { JOIN, CHAT, LEAVE }

    private Type type;      // JOIN / CHAT / LEAVE
    private String roomId;  // 확장 대비 (단일 방이면 생략 가능)
    private String sender;  // 닉네임/유저명
    private String content; // 메시지 본문
    private long   timestamp; // 클라이언트/서버측에서 넣어도 OK
}
