package com.s14.petshop.model.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "addresses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String city;
    @Column
    private String postcode;
    @Column
    private String streetAddress;
    @Column
    private String apartmentBuilding;
    @Column
    private String apartmentNumber;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
