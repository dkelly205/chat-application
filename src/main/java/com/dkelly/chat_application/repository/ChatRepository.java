package com.dkelly.chat_application.repository;

import com.dkelly.chat_application.entity.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Page<Chat> findByCompanyId(Long companyId, Pageable pageable);

    Page<Chat> findByCustomerId(Long userId, Pageable pageable);

    Page<Chat> findByCompanyIdAndNameContainingIgnoreCase(
            Long companyId, String keyword, Pageable pageable);

    Page<Chat> findByCustomerIdAndNameContainingIgnoreCase(
            Long userId, String keyword, Pageable pageable);

    Page<Chat> findByAssignedUserId(Long userId, Pageable pageable);

    Page<Chat> findByAssignedUserIdAndNameContainingIgnoreCase(
            Long userId, String keyword, Pageable pageable);
}
