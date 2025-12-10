package com.dkelly.chat_application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Chat {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Company company;

    @ManyToOne
    private User customer;

    @ManyToOne
    private User assignedUser;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Message> messages;
}
