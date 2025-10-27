package com.smartchat.service;

import java.util.List;
import com.smartchat.dto.ChatDto;

public interface ChatService {

    // 전체 조회
    List<ChatDto> selectAll();

    // 단일 조회
    ChatDto selectById(int qaId);

    // 삽입
    int insertQA(ChatDto dto);

    // 수정
    int updateQA(ChatDto dto);

    // 삭제
    int deleteQA(int qaId);

    // ✅ 추가: 사용자의 질문에 맞는 답변 찾기
    String findAnswer(String question);
    
    //gpt fallback
    String askGPT(String question); 
}
