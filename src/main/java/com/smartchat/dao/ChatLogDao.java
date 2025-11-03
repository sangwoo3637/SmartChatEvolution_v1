package com.smartchat.dao;

import com.smartchat.dto.ChatLogDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ChatLogDao {
    void insertLog(ChatLogDto dto);
    List<ChatLogDto> selectAll(); // ✅ 추가
}
