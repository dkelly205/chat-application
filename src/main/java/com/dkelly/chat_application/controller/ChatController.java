package com.dkelly.chat_application.controller;

import com.dkelly.chat_application.dto.chat.ChatRequest;
import com.dkelly.chat_application.dto.chat.ChatResponse;
import com.dkelly.chat_application.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatResponse create(@RequestBody ChatRequest request) {
        return chatService.create(request);
    }

    @GetMapping(params = "companyId")
    public Page<ChatResponse> getByCompany(
            @RequestParam Long companyId,
            @RequestParam(required = false) String searchTerm,
            Pageable pageable
    ) {
        return chatService.findByCompany(companyId, searchTerm, pageable);
    }

    @GetMapping(params = "customerId")
    public Page<ChatResponse> getByCustomer(
            @RequestParam Long customerId,
            @RequestParam(required = false) String searchTerm,
            Pageable pageable
    ) {
        return chatService.findByCustomer(customerId, searchTerm, pageable);
    }

    @GetMapping(params = "assignedUserId")
    public Page<ChatResponse> getByAssignedUser(
            @RequestParam Long assignedUserId,
            @RequestParam(required = false) String searchTerm,
            Pageable pageable
    ) {
        return chatService.findByAssignedUser(assignedUserId, searchTerm, pageable);
    }


}
