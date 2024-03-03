package com.example.bankservice.service;

import com.example.bankservice.entity.PhoneNumberEntity;
import com.example.bankservice.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {

    public void addPhoneNumberToUser(UserEntity user, String number) {
        PhoneNumberEntity phoneNumberEntity = new PhoneNumberEntity();
        phoneNumberEntity.setNumber(number);
        phoneNumberEntity.setUser(user);

        user.getPhoneNumbers().add(phoneNumberEntity);
    }
}
