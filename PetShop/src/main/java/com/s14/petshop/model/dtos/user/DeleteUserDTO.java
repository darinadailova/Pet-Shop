package com.s14.petshop.model.dtos.user;

import lombok.Data;

@Data
public class DeleteUserDTO {

    private String password;
    private String confirm_password;

}
