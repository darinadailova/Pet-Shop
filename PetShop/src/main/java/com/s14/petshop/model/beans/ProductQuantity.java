package com.s14.petshop.model.beans;

import com.s14.petshop.model.compositekeys.ProductQuantityKey;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "orders_have_products")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductQuantity {

    @EmbeddedId
    ProductQuantityKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    Order order;

    int quantity;
}
