package com.dkelly.chat_application.controller;

import com.dkelly.chat_application.dto.chat.ChatRequest;
import com.dkelly.chat_application.dto.chat.ChatResponse;
import com.dkelly.chat_application.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping
    public Page<ChatResponse> findAll(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(value="offset", required = false) Integer offset,
            @RequestParam(value="pageSize", required = false) Integer pageSize,
            @RequestParam(value="sortBy", required=false) String sortBy) {

        if(null == offset) offset = 0;
        if(null == pageSize) pageSize = 10;
        if(StringUtils.isEmpty(sortBy)) sortBy = "id";

        return chatService.findAll(companyId, userId, searchTerm, PageRequest.of(offset, pageSize, Sort.by(sortBy)));
    }


}
