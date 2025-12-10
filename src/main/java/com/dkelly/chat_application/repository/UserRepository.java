package com.dkelly.chat_application.repository;

import com.dkelly.chat_application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
