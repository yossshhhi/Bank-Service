package com.example.bankservice.service;

import com.example.bankservice.entity.BankAccountEntity;
import com.example.bankservice.entity.PhoneNumberEntity;
import com.example.bankservice.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankAccountService {

    public void createBankAccount(UserEntity user, BigDecimal number) {
        BankAccountEntity account = new BankAccountEntity();
        account.setBalance(number);
        account.setUser(user);

        user.setBankAccount(account);
    }
}
