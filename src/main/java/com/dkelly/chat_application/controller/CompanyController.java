package com.dkelly.chat_application.controller;

import com.dkelly.chat_application.dto.company.CompanyRequest;
import com.dkelly.chat_application.dto.company.CompanyResponse;
import com.dkelly.chat_application.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public CompanyResponse create(@RequestBody CompanyRequest request) {
        return companyService.create(request);
    }
}
