package com.example.bankservice.service;

import com.example.bankservice.entity.EmailEntity;
import com.example.bankservice.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void addEmailToUser(UserEntity user, String email) {
        EmailEntity newEmail = new EmailEntity();
        newEmail.setEmailAddress(email);
        newEmail.setUser(user);

        user.getEmails().add(newEmail);
    }
}
