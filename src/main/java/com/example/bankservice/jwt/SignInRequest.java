package com.example.bankservice.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Authentication request")
public class SignInRequest {

    @Schema(description = "Username", example = "Yoshi")
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
//    @Pattern(regexp = "^[a-zA-Z0-9_.]+$", message = "Username can only consist of Latin letters, numbers, underscores, and periods")
    private String username;

    @Schema(description = "Password", example = "my_1seCRet1_password")
    @Size(min = 6, max = 255, message = "Password length must be no more than 255 characters")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%\\-^&*()_+~\"№;:?=\\[\\]{}|,.'<>])[a-zA-Z\\d!@#$%\\-^&*()_+~\"№;:?=\\[\\]{}|,.'<>]{6,255}$", message = "Password must contain upper and lower case Latin letters, numbers, and special characters")
    private String password;
}
