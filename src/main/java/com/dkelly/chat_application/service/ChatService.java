package com.dkelly.chat_application.service;

import com.dkelly.chat_application.dto.chat.ChatRequest;
import com.dkelly.chat_application.dto.chat.ChatResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ChatService {

    ChatResponse create(ChatRequest request);

    Page<ChatResponse> findByCompany(Long companyId, String searchTerm, Pageable pageable);

    Page<ChatResponse> findByCustomer(Long customerId, String searchTerm, Pageable pageable);

    Page<ChatResponse> findByAssignedUser(Long userId, String searchTerm, Pageable pageable);
}
