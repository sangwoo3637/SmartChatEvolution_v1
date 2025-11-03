package com.smartchat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartchat.dao.ChatLogDao;
import com.smartchat.dto.ChatLogDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatLogServiceImpl implements ChatLogService {

    private final ChatLogDao dao;

    @Override
    public void save(ChatLogDto dto) {
        dao.insertLog(dto);
    }

    @Override
    public List<ChatLogDto> findAll() {
        return dao.selectAll();
    }
}
