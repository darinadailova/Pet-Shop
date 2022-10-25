package com.s14.petshop.model.dtos.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegisterDTO {

    @NotBlank(message = "First name is required")
    @Length(min = 2, max = 45, message = "First name should be between 2 and 45 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Length(min = 2, max = 45, message = "First name should be between 2 and 45 characters")
    private String lastName;

    @NotBlank(message = "Email address is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\+359[0-9]{9}", message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "Gender is required")
    @Length(max = 1, message = "Gender field can be only 1 character")
    private String gender;

    private boolean isSubscribed;
}
