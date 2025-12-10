package com.dkelly.chat_application.dto.chat;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponse {

    private Long id;
    private String name;
    private Long companyId;
    private Long customerId;
    private Long assignedUserId;
}
