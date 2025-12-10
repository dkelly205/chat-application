package com.dkelly.chat_application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User sender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chat_id")
    private Chat chat;

}
