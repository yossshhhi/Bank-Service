package com.example.bankservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jdk.jfr.Timestamp;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
//@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailEntity> emails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumberEntity> phoneNumbers;

    @NotNull(message = "Required field, please enter your first name")
    @Size(min = 2, max = 50, message = "The first name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "The first name can only consist of Latin letters")
    @Column(length = 50)
    private String firstName;

    @NotNull(message = "Required field, please enter your last name")
    @Size(min = 2, max = 50, message = "The last name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "The last name can only consist of Latin letters")
    @Column(length = 50)
    private String lastName;

    @NotNull(message = "Required field, please enter your patronymic")
    @Size(min = 2, max = 50, message = "The patronymic must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "The patronymic can only consist of Latin letters")
    @Column(length = 50)
    private String patronymic;

    @NotNull
    @Past
    private LocalDate birthDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private BankAccountEntity bankAccount;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
