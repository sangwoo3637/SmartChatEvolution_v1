package com.smartchat.controller;

import com.smartchat.dto.ChatDto;
import com.smartchat.dto.ChatLogDto;
import com.smartchat.service.ChatLogService;
import com.smartchat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatLogService chatLogService;

    // ✅ 1. FAQ 목록
    @GetMapping("/faq")
    public String faqList(Model model) {
        List<ChatDto> list = chatService.selectAll();
        model.addAttribute("faqList", list);
        return "adminFaq";
    }

    // ✅ 2. FAQ CRUD
    @PostMapping("/insert")
    public String insert(ChatDto dto) {
        chatService.insertQA(dto);
        return "redirect:/admin/faq";
    }

    @PostMapping("/update")
    public String update(ChatDto dto) {
        chatService.updateQA(dto);
        return "redirect:/admin/faq";
    }

    @GetMapping("/delete/{qaId}")
    public String delete(@PathVariable int qaId) {
        chatService.deleteQA(qaId);
        return "redirect:/admin/faq";
    }

    // ✅ 3. 로그 목록 페이지
    @GetMapping("/logs")
    public String adminLogsPage() {
        return "adminLogs";
    }

    // ✅ 4. 통계 대시보드 페이지
    @GetMapping("/stats")
    public String adminStatsPage() {
        return "adminStats";
    }

    // ✅ 5. 통계 데이터 (JSON)
    @GetMapping("/stats/data")
    @ResponseBody
    public Map<String, Object> getStatsData() {
        Map<String, Object> data = new HashMap<>();
        List<ChatLogDto> logs = chatLogService.findAll();

        // 1️⃣ 시간대별 메시지 수 (LocalDateTime 대응)
        Map<String, Long> byHour = logs.stream()
                .filter(log -> log.getTimestamp() != null)
                .collect(Collectors.groupingBy(
                        log -> log.getTimestamp()
                                .format(DateTimeFormatter.ofPattern("HH")),
                        TreeMap::new,
                        Collectors.counting()
                ));
        data.put("byHour", byHour);

        // 2️⃣ 사용자별 발화량
        Map<String, Long> byUser = logs.stream()
                .filter(log -> log.getSender() != null)
                .collect(Collectors.groupingBy(
                        ChatLogDto::getSender,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        data.put("byUser", byUser);

        // 3️⃣ 메시지 유형 비율 (JOIN / CHAT)
        Map<String, Long> byType = logs.stream()
                .filter(log -> log.getType() != null)
                .collect(Collectors.groupingBy(
                        ChatLogDto::getType,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        data.put("byType", byType);

        return data;
    }
}
