# 🧠 SmartChatEvolution_v1
**Level 1: FAQ 기반 Chatbot (베이스 버전)**

Spring Boot + MyBatis + JSP + Ajax + Oracle + OpenAI API 구조로 개발된 FAQ 기반 챗봇 프로젝트입니다.

## ✨ 주요 기능
- 사용자 입력 → DB에 저장된 질문/답변 매칭
- DB에 없을 경우 ChatGPT API fallback
- 관리자 페이지에서 FAQ CRUD 관리 가능

## ⚙️ 기술 스택
- **Backend**: Spring Boot 3.3.4, MyBatis, Oracle XE  
- **Frontend**: JSP, Ajax  
- **API**: OpenAI (ChatGPT), HuggingFace (옵션)

---

> ⚡ Level 2부터는 실시간 WebSocket + Logging Dashboard,  
> Level 3는 Intent Classification 기반 Custom AI Fine-tuning 버전으로 확장 예정입니다.
