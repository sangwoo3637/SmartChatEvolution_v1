package com.smartchat.service;

import java.util.List;
import com.smartchat.dto.ChatDto;

public interface ChatService {

    List<ChatDto> selectAll();

    ChatDto selectById(int qaId);

    int insertQA(ChatDto dto);

    int updateQA(ChatDto dto);

    int deleteQA(int qaId);

    String findAnswer(String question);
    
    String askGPT(String question); 
}
