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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    private static Page<ChatResponse> mapToChatResponse(Page<Chat> chats) {
        return chats.map(chat -> new ChatResponse(
                chat.getId(),
                chat.getName(),
                chat.getCompany().getId(),
                chat.getCustomer().getId(),
                chat.getAssignedUser().getId()
        ));
    }

    @Override
    public Page<ChatResponse> findByCompany(Long companyId, String searchTerm, Pageable pageable) {
        boolean hasSearch = (searchTerm != null && !searchTerm.isBlank());

        Page<Chat> chats;
        if(hasSearch){
            chats = chatRepository.findByCompanyIdAndNameContainingIgnoreCase(companyId, searchTerm, pageable);
        } else {
            chats = chatRepository.findByCompanyId(companyId, pageable);
        }
        return mapToChatResponse(chats);

    }

    @Override
    public Page<ChatResponse> findByCustomer(Long customerId, String searchTerm, Pageable pageable) {
        boolean hasSearch = (searchTerm != null && !searchTerm.isBlank());

        Page<Chat> chats;
        if (hasSearch) {
            chats = chatRepository.findByCustomerIdAndNameContainingIgnoreCase(
                    customerId,
                    searchTerm,
                    pageable);
        } else {
            chats = chatRepository.findByCustomerId(customerId, pageable);
        }
        return mapToChatResponse(chats);

    }

    @Override
    public Page<ChatResponse> findByAssignedUser(Long userId, String searchTerm, Pageable pageable) {
        boolean hasSearch = (searchTerm != null && !searchTerm.isBlank());
        Page<Chat> chats;
        if (hasSearch) {
            chats = chatRepository.findByAssignedUserIdAndNameContainingIgnoreCase(
                    userId,
                    searchTerm,
                    pageable);
        } else {
            chats = chatRepository.findByAssignedUserId(userId, pageable);
        }
        return mapToChatResponse(chats);

    }
}
