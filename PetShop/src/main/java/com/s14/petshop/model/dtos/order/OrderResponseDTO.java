package com.s14.petshop.model.dtos.order;

import com.s14.petshop.model.beans.Address;
import com.s14.petshop.model.beans.ProductQuantity;
import com.s14.petshop.model.beans.User;
import com.s14.petshop.model.dtos.product.ProductResponseDTO;
import com.s14.petshop.model.dtos.user.UserWithoutPasswordDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class OrderResponseDTO {
    private int id;

    private double price;

    private LocalDateTime orderedAt;

    List<ProductResponseDTO> products;

    private UserWithoutPasswordDTO orderedBy;

}
