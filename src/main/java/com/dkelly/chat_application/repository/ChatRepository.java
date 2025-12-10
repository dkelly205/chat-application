package com.dkelly.chat_application.repository;

import com.dkelly.chat_application.entity.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Page<Chat> findByCompanyId(Long companyId, Pageable pageable);

    // All chats a user belongs to
    Page<Chat> findByMembersId(Long userId, Pageable pageable);

    Page<Chat> findByCompanyIdAndNameContainingIgnoreCase(
            Long companyId, String keyword, Pageable pageable);

    Page<Chat> findByMembersIdAndNameContainingIgnoreCase(
            Long userId, String keyword, Pageable pageable);
}
