package com.dkelly.chat_application.dto.message;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MessageResponse {

    private Long id;
    private Long chatId;
    private Long senderId;
    private String content;
}
