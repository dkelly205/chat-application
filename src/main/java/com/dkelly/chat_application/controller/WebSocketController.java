package com.dkelly.chat_application.controller;

import com.dkelly.chat_application.dto.message.MessageRequest;
import com.dkelly.chat_application.dto.message.MessageResponse;
import com.dkelly.chat_application.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/chat.send")
    public MessageResponse sendMessage(@Payload MessageRequest request) {

        MessageResponse saved =  messageService.create(request);
        // Broadcast to subscribers
        messagingTemplate.convertAndSend(
               "/topic/chat." + saved.getChatId(),
                saved
        );

        return saved;
    }
}
