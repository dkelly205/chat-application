package com.dkelly.chat_application.service;

import com.dkelly.chat_application.dto.chat.ChatRequest;
import com.dkelly.chat_application.dto.chat.ChatResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface ChatService {

    ChatResponse create(ChatRequest request);

    Page<ChatResponse> findAll(Long companyId, Long userId, String searchTerm, PageRequest pageRequest);
}
