package com.s14.petshop.model.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "reviews")
@Setter
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int rating;
    @Column
    private String comment;
    @Column
    private LocalDateTime postedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    // todo add many to one relationship for product id

}
