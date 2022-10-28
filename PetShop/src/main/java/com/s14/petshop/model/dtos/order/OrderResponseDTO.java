package com.s14.petshop.model.dtos.order;

import com.s14.petshop.model.dtos.address.AddingAddress;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponseDTO {

    private int id;
    private double price;
    private LocalDateTime orderedAt;
    private AddingAddress addressForDelivery;
}
