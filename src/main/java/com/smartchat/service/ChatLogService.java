package com.smartchat.service;

import com.smartchat.dto.ChatLogDto;
import java.util.List;

public interface ChatLogService {
    void save(ChatLogDto dto);
    List<ChatLogDto> findAll(); // ✅ 추가
}
