package com.smartchat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smartchat.dto.ChatDto;

@Mapper
public interface ChatDao {

    List<ChatDto> selectAll();

    ChatDto selectById(int qaId);

    int insertQA(ChatDto dto);

    int updateQA(ChatDto dto);

    int deleteQA(int qaId);
}
