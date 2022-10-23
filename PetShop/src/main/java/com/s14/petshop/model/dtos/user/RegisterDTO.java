package com.s14.petshop.model.dtos.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email address is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Gender is required")
    private String gender;

    private boolean isSubscribed;
}
