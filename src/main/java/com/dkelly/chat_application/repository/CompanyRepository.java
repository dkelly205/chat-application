package com.dkelly.chat_application.repository;

import com.dkelly.chat_application.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
