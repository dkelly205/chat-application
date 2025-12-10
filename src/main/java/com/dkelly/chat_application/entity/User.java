package com.dkelly.chat_application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @ManyToMany(mappedBy = "members")
    private List<Chat> chats = new ArrayList<>();
}
