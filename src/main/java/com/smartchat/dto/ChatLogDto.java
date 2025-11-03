package com.smartchat.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatLogDto {
    private Long logId;
    private String sender;
    private String message;
    private String type;
    private LocalDateTime timestamp;
}
