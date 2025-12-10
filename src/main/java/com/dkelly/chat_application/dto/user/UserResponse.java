package com.dkelly.chat_application.dto.user;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private Long companyId;
}
