package com.smartchat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smartchat.dto.ChatDto;

@Mapper
public interface ChatDao {

    // ✅ 전체 조회
    List<ChatDto> selectAll();

    // ✅ 단일 조회
    ChatDto selectById(int qaId);

    // ✅ 데이터 추가
    int insertQA(ChatDto dto);

    // ✅ 데이터 수정
    int updateQA(ChatDto dto);

    // ✅ 데이터 삭제
    int deleteQA(int qaId);
}
