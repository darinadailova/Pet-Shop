package com.s14.petshop.model.dtos.user;

import lombok.Data;

@Data
public class ChangePasswordDTO {

    private String password;
    private String newPassword;
    private String repeatNewPassword;

}
