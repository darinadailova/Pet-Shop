package com.s14.petshop.model.dtos.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordDTO {

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "New password is required")
    private String newPassword;

    @NotBlank(message = "Repeat password is required")
    private String repeatNewPassword;
}
