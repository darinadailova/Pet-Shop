package com.s14.petshop.model.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Setter
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private double price;
    @Column
    private LocalDateTime orderedAt;

    @OneToMany(mappedBy = "order")
    List<ProductQuantity> quantities;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User orderedBy;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address addressForDelivery;

    //todo add relation between user,address and order
}
