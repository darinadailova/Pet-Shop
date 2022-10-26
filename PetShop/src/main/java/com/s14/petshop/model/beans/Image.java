package com.s14.petshop.model.beans;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "images")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product owner;
}
