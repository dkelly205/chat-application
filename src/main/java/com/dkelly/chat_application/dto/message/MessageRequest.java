package com.dkelly.chat_application.dto.message;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MessageRequest {
    private Long chatId;
    private Long senderId;
    private String content;

}
