package com.s14.petshop.model.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "products")
@Setter
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private double price;
    @Column
    private int quantity;
    @Column
    private String info;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToMany(mappedBy = "favProducts")
    private List<User> favoredBy;

    @OneToMany(mappedBy = "owner")
    private List<Image> images;

    @OneToMany(mappedBy = "reviewedProduct")
    private List<Review> reviews;

    @OneToMany(mappedBy = "product")
    private List<ProductQuantity> quantities;

}
