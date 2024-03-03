package com.example.bankservice.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "Registration request")
public class SignUpRequest extends SignInRequest{

    @Schema(description = "Email address", example = "yoshioka@gmail.com")
    @Size(min = 5, max = 255, message = "The email address must contain between 5 and 255 characters")
    @NotBlank(message = "Email address cannot be empty")
    @Email(message = "Email address must be in the format user@example.com")
    private String email;

    @Schema(description = "Phone number", example = "+78887770090")
    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+7\\d{9}$", message = "Invalid number, example: +78887770090")
    private String phoneNumber;

    @Schema(description = "Initial amount", example = "500")
    @Positive(message = "Initial amount must be a positive value greater than zero")
    private Long initialAmount;

}
