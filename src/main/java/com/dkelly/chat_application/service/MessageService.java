package com.dkelly.chat_application.service;

import com.dkelly.chat_application.dto.message.MessageRequest;
import com.dkelly.chat_application.dto.message.MessageResponse;
import com.dkelly.chat_application.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface MessageService {

    MessageResponse create(MessageRequest request);

    Page<Message> getChatMessages(Long chatId, PageRequest pageRequest);


}
