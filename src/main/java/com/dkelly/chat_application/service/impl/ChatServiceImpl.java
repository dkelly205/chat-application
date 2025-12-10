package com.dkelly.chat_application.service.impl;

import com.dkelly.chat_application.dto.chat.ChatRequest;
import com.dkelly.chat_application.dto.chat.ChatResponse;
import com.dkelly.chat_application.entity.Chat;
import com.dkelly.chat_application.entity.Company;
import com.dkelly.chat_application.entity.User;
import com.dkelly.chat_application.repository.ChatRepository;
import com.dkelly.chat_application.repository.CompanyRepository;
import com.dkelly.chat_application.repository.UserRepository;
import com.dkelly.chat_application.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Override
    public ChatResponse create(ChatRequest request) {
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Optional<User> assignedUser = userRepository.findById(request.getAssignedUserId());

        Optional<User> customer = userRepository.findById(request.getCustomerId());

        Chat chat = Chat.builder()
                .name(request.getName())
                .company(company)
                .build();

        customer.ifPresent(chat::setCustomer);
        assignedUser.ifPresent(chat::setAssignedUser);

        chatRepository.save(chat);

        return new ChatResponse(
                chat.getId(),
                chat.getName(),
                chat.getCompany().getId(),
                chat.getCustomer().getId(),
                chat.getAssignedUser().getId()
        );
    }

    @Override
    public Page<ChatResponse> findAll(Long companyId, Long userId, String searchTerm, PageRequest pageRequest) {
        Page<Chat> chats;

        boolean hasSearch = (searchTerm != null && !searchTerm.isBlank());

        if (companyId != null) {
            if(hasSearch){
                chats = chatRepository.findByCompanyIdAndNameContainingIgnoreCase(companyId, searchTerm, pageRequest);
            } else {
                chats = chatRepository.findByCompanyId(companyId, pageRequest);
            }
        } else if (userId != null) {
            if (hasSearch) {
                chats = chatRepository.findByMembersIdAndNameContainingIgnoreCase(
                        userId,
                        searchTerm,
                        pageRequest);
            } else {
                chats = chatRepository.findByMembersId(userId, pageRequest);
            }
        } else {
            throw new IllegalArgumentException("Either companyId or userId must be provided");
        }

        return chats.map(chat -> new ChatResponse(
                chat.getId(),
                chat.getName(),
                chat.getCompany().getId(),
                chat.getCustomer().getId(),
                chat.getAssignedUser().getId()
        ));

    }
}
