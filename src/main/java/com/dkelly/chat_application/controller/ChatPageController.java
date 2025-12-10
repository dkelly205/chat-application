package com.dkelly.chat_application.controller;

import com.dkelly.chat_application.repository.ChatRepository;
import com.dkelly.chat_application.repository.UserRepository;
import com.dkelly.chat_application.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ChatPageController {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageService messageService;

    @GetMapping("/chat/{chatId}")
    public String chatPage(@PathVariable Long chatId, Model model, @RequestParam Long userId) {
        model.addAttribute("chatId", chatId);
        model.addAttribute("userId", userId);

        // Optionally load last 50 messages
        var messages = messageService.getChatMessages(chatId, PageRequest.of(0,50)).getContent();
        model.addAttribute("messages", messages);

        return "chat"; // Thymeleaf template chat.html
    }

}
