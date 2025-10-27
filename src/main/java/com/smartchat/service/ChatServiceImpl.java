package com.smartchat.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.smartchat.dao.ChatDao;
import com.smartchat.dto.ChatDto;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao dao;

    @Override
    public List<ChatDto> selectAll() {
        return dao.selectAll();
    }

    @Override
    public ChatDto selectById(int qaId) {
        return dao.selectById(qaId);
    }

    @Override
    public int insertQA(ChatDto dto) {
        return dao.insertQA(dto);
    }

    @Override
    public int updateQA(ChatDto dto) {
        return dao.updateQA(dto);
    }

    @Override
    public int deleteQA(int qaId) {
        return dao.deleteQA(qaId);
    }

    @Override
    public String findAnswer(String question) {
        List<ChatDto> list = dao.selectAll();
        for (ChatDto dto : list) {
            if (question.contains(dto.getQuestion())) {
                return dto.getAnswer();
            }
        }
        return null;
    }

    @Override
    public String askGPT(String question) {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            apiKey = "sk-여기에_API_KEY_입력"; 
        }

        String endpoint = "https://api.openai.com/v1/chat/completions";
        String prompt = "다음 고객 질문에 친절하게 답변해주세요: " + question;

        String requestBody = "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]"
                + "}";

        RestTemplate rest = new RestTemplate();
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        headers.add("Content-Type", "application/json");

        org.springframework.http.HttpEntity<String> entity =
                new org.springframework.http.HttpEntity<>(requestBody, headers);

        try {
            String response = rest.postForObject(endpoint, entity, String.class);
            int start = response.indexOf("content") + 11;
            int end = response.indexOf("\"", start);
            return response.substring(start, end)
                    .replace("\\n", " ")
                    .replace("\\", "");
        } catch (Exception e) {
            e.printStackTrace();
            return "현재 AI 서버 응답이 지연 중입니다 ";
        }
    }
}
