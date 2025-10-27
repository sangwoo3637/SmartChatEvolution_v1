package com.smartchat.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartchat.dto.ChatDto;
import com.smartchat.service.ChatService;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    // FAQ 목록 (JSON)
    @GetMapping("/chat/list")
    @ResponseBody
    public List<ChatDto> getChatList() {
        return chatService.selectAll();
    }

    // 챗봇 화면
    @GetMapping("/chat")
    public String chatPage() {
        System.out.println(">>> chat.html 요청 들어옴 ✅");
        return "chat";
    }

    // 사용자 메시지 처리
    @PostMapping("/chat/ask")
    @ResponseBody
    public Map<String, String> askBot(@RequestBody Map<String, String> req) {
        String userMsg = req.get("message");
        String botReply = chatService.findAnswer(userMsg);

        if (botReply == null || botReply.isEmpty()) {
            botReply = chatService.askGPT(userMsg);  // ✅ GPT fallback
        }

        Map<String, String> res = new HashMap<>();
        res.put("bot", botReply);
        return res;
    }
}
