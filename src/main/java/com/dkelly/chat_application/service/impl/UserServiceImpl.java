package com.dkelly.chat_application.service.impl;

import com.dkelly.chat_application.dto.user.UserRequest;
import com.dkelly.chat_application.dto.user.UserResponse;
import com.dkelly.chat_application.entity.Company;
import com.dkelly.chat_application.entity.User;
import com.dkelly.chat_application.repository.CompanyRepository;
import com.dkelly.chat_application.repository.UserRepository;
import com.dkelly.chat_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        Company company = null; // default is null

        if (userRequest.getCompanyId() != null) {
            company = companyRepository.findById(userRequest.getCompanyId()).orElse(null);
        }

        User user = User.builder()
                .username(userRequest.getUsername())
                .company(company)   // may be null
                .build();

        userRepository.save(user);

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                company != null ? company.getId() : null
        );
    }
}
