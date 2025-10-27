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

    @GetMapping("/chat/list")
    @ResponseBody
    public List<ChatDto> getChatList() {
        return chatService.selectAll();
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }

    @PostMapping("/chat/ask")
    @ResponseBody
    public Map<String, String> askBot(@RequestBody Map<String, String> req) {
        String userMsg = req.get("message");
        String botReply = chatService.findAnswer(userMsg);

        if (botReply == null || botReply.isEmpty()) {
            botReply = chatService.askGPT(userMsg); 
        }

        Map<String, String> res = new HashMap<>();
        res.put("bot", botReply);
        return res;
    }
}
