package com.s14.petshop.model.dtos.orders;

import com.s14.petshop.model.beans.Address;
import com.s14.petshop.model.beans.ProductQuantity;
import com.s14.petshop.model.beans.User;
import com.s14.petshop.model.dtos.address.AddingAddress;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {

    private int id;
    private double price;
    private LocalDateTime orderedAt;
    private AddingAddress addressForDelivery;
}
