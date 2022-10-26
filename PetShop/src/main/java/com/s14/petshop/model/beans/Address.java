package com.s14.petshop.model.beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "addressForDelivery")
    private List<Order> orders;
    // todo add one to many relationship with orders

    public boolean equals(Address obj) {
        if (! (this.owner.getId() == obj.owner.getId() && this.city.equals(obj.getCity()) &&
                this.postcode.equals(obj.getPostcode()) && this.streetAddress.equals(obj.getStreetAddress()))) {
            return false;
        }
        if (obj.getApartmentBuilding() != null) {
            if (!this.apartmentBuilding.equals(obj.getApartmentBuilding())) {
                return false;
            }
        }
        if (obj.getApartmentNumber() != null) {
            if (!this.apartmentNumber.equals(obj.getApartmentNumber())) {
                return false;
            }
        }
        return true;
    }
}
