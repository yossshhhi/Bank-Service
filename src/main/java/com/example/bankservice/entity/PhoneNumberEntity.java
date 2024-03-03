package com.example.bankservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "phone_number")
public class PhoneNumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number",unique = true, nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;
}
