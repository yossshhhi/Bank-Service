package com.example.bankservice.service;

import com.example.bankservice.entity.EmailEntity;
import com.example.bankservice.entity.PhoneNumberEntity;
import com.example.bankservice.entity.UserEntity;
import com.example.bankservice.jwt.SignUpRequest;
import com.example.bankservice.repository.EmailRepository;
import com.example.bankservice.repository.PhoneNumberRepository;
import com.example.bankservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public void isUserDataUnique(SignUpRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("A user with the same username already exists");
        }

        if (emailRepository.existsByEmailAddress(request.getEmail())) {
            throw new RuntimeException("A user with the same email address already exists");
        }

        if (phoneNumberRepository.existsByNumber(request.getPhoneNumber())) {
            throw new RuntimeException("A user with the same phone number already exists");
        }
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public UserEntity getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
}
