package com.s14.petshop.model.dtos.user;

import com.s14.petshop.model.beans.Address;
import com.s14.petshop.model.beans.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class RegisterDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private String gender;
    private boolean isSubscribed;
}
