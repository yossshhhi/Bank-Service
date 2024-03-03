package com.example.bankservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "email")
public class EmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;
}
