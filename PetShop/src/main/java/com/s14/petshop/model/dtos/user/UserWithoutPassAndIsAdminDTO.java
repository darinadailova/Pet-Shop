package com.s14.petshop.model.dtos.user;

import com.s14.petshop.model.beans.Address;
import com.s14.petshop.model.beans.Review;
import lombok.Data;

import java.util.List;

@Data
public class UserWithoutPassAndIsAdminDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;
    private boolean isSubscribed;
    private List<Address> addresses;
    private List<Review> reviews;
}
