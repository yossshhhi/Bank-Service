package com.example.bankservice.repository;

import com.example.bankservice.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
    boolean existsByEmailAddress(String emailAddress);
}
