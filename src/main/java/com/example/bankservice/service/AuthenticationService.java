package com.example.bankservice.service;

import com.example.bankservice.entity.BankAccountEntity;
import com.example.bankservice.entity.UserEntity;
import com.example.bankservice.jwt.JwtAuthenticationResponse;
import com.example.bankservice.jwt.SignInRequest;
import com.example.bankservice.jwt.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final EmailService emailService;
    private final PhoneNumberService phoneNumberService;
    private final BankAccountService bankAccountService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        emailService.addEmailToUser(userEntity, request.getEmail());
        phoneNumberService.addPhoneNumberToUser(userEntity, request.getPhoneNumber());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        bankAccountService.createBankAccount(userEntity, BigDecimal.valueOf(request.getInitialAmount()));

        userEntity = userService.save(userEntity);

        var jwt = jwtService.generateToken(userEntity);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
