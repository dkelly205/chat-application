package com.dkelly.chat_application.service;

import com.dkelly.chat_application.dto.company.CompanyRequest;
import com.dkelly.chat_application.dto.company.CompanyResponse;
import com.dkelly.chat_application.repository.CompanyRepository;



public interface CompanyService {

    public CompanyResponse create(CompanyRequest companyRequest);
}
