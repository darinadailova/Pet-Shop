package com.s14.petshop.model.compositekeys;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class ProductQuantityKey implements Serializable {

    @Column(name = "order_id")
    int orderId;

    @Column(name = "product_id")
    int productId;
}
