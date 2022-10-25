package com.s14.petshop.model.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "discounts")
@Setter
@Getter
@NoArgsConstructor

public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int percentDiscount;
    @Column
    private LocalDateTime startAt;
    @Column
    private LocalDateTime endAt;

    @OneToMany(mappedBy = "discount")
    private List<Product> products;
}
