package com.s14.petshop.model.repositories;

import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.beans.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Integer> {


    List<ProductQuantity> findAllByOrder_Id(int order_id);
}
