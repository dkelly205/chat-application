package com.dkelly.chat_application.service.impl;

import com.dkelly.chat_application.dto.message.MessageRequest;
import com.dkelly.chat_application.dto.message.MessageResponse;
import com.dkelly.chat_application.entity.Chat;
import com.dkelly.chat_application.entity.Message;
import com.dkelly.chat_application.entity.User;
import com.dkelly.chat_application.repository.ChatRepository;
import com.dkelly.chat_application.repository.MessageRepository;
import com.dkelly.chat_application.repository.UserRepository;
import com.dkelly.chat_application.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Override
    public MessageResponse create(MessageRequest request) {

        Chat chat = chatRepository.findById(request.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        User sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Message msg = Message.builder()
                .content(request.getContent())
                .sender(sender)
                .chat(chat)
                .build();

        messageRepository.save(msg);

        return new MessageResponse(
                msg.getId(),
                chat.getId(),
                sender.getId(),
                msg.getContent()
        );
    }

    public Page<Message> getChatMessages(Long chatId, PageRequest pageRequest) {
        return messageRepository.findByChatIdOrderByTimestampAsc(chatId, pageRequest);
    }
}
