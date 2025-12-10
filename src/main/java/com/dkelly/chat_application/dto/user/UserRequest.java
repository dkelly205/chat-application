package com.dkelly.chat_application.dto.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {
    private String username;
    private Long companyId;
}
