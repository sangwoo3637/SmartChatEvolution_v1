package com.smartchat.dto;

import lombok.Data;

@Data
public class ChatDto {
    private int qaId;        // 질문 ID
    private String question; // 사용자 질문
    private String answer;   // 챗봇 답변
}
