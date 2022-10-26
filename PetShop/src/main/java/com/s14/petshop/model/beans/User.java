package com.s14.petshop.model.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @Column
    private String profilePictureUrl;

    @OneToMany(mappedBy = "owner")
    private List<Address> addresses;

    @OneToMany(mappedBy = "owner")
    private List<Review> reviews;

    // todo add one to many relationship with orders
    @ManyToMany
    @JoinTable(
            name = "users_have_favorite_products",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    Set<Product> likedProducts;
}
