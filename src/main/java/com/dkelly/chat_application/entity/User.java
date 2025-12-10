package com.dkelly.chat_application.entity;

import jakarta.persistence.*;
import lombok.*;


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

}
