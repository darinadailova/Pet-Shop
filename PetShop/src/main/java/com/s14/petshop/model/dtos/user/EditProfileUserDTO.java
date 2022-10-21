package com.s14.petshop.model.dtos.user;
import lombok.Data;

@Data
public class EditProfileUserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
    private String phoneNumber;
    private String gender;
}
