package com.s14.petshop.model.repositories;

import com.s14.petshop.model.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);
}
