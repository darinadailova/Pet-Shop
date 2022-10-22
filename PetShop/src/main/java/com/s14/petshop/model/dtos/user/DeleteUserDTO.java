package com.s14.petshop.model.dtos.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteUserDTO {

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirm_password;

}
