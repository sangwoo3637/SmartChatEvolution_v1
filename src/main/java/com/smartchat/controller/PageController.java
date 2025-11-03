// src/main/java/com/smartchat/controller/PageController.java
package com.smartchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // 실시간 채팅 테스트 페이지
    @GetMapping("/chat/ws")
    public String chatWsPage() {
        // templates/chat_ws.html 을 렌더링
        return "chat_ws";
    }
}
