package com.s14.petshop.model.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@Setter
@Getter
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String phoneNumber;
    @Column
    private String gender;
    @Column
    private boolean isAdmin;
    @Column
    private boolean isSubscribed;

    @OneToMany(mappedBy = "owner")
    private List<Address> addresses;

    @OneToMany(mappedBy = "owner")
    private List<Review> reviews;

    // todo add many to many relationship for favorite products
}
