package com.smartchat.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.smartchat.dto.ChatDto;
import com.smartchat.service.ChatService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ChatService chatService;

    // ✅ FAQ 목록 페이지
    @GetMapping("/faq")
    public String faqList(Model model) {
        List<ChatDto> list = chatService.selectAll();
        model.addAttribute("faqList", list);
        return "adminFaq"; // templates/adminFaq.html 로 이동
    }

    // ✅ 새 FAQ 등록
    @PostMapping("/insert")
    public String insert(ChatDto dto) {
        chatService.insertQA(dto);
        return "redirect:/admin/faq";
    }

    // ✅ FAQ 수정
    @PostMapping("/update")
    public String update(ChatDto dto) {
        chatService.updateQA(dto);
        return "redirect:/admin/faq";
    }

    // ✅ FAQ 삭제
    @GetMapping("/delete/{qaId}")
    public String delete(@PathVariable int qaId) {
        chatService.deleteQA(qaId);
        return "redirect:/admin/faq";
    }
}
