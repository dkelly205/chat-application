package com.dkelly.chat_application.service.impl;

import com.dkelly.chat_application.dto.company.CompanyRequest;
import com.dkelly.chat_application.dto.company.CompanyResponse;
import com.dkelly.chat_application.entity.Company;
import com.dkelly.chat_application.repository.CompanyRepository;
import com.dkelly.chat_application.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse create(CompanyRequest companyRequest) {
        Company company = Company.builder()
                .name(companyRequest.getName())
                .build();
        companyRepository.save(company);
        return new CompanyResponse(company.getId(), company.getName());
    }


}
