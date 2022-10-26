package com.s14.petshop.model.repositories;

import com.s14.petshop.model.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByNameIsLikeIgnoreCase(String name);
    Optional<Product> findProductById(int id);
    Optional<Product> findByName(String name);

    List<Product> findByPriceAfter(double price);

    List<Product> findByPriceBefore(double price);
    List<Product> findByPriceBetweenOrPriceGreaterThanOrPriceBefore
            (double price, double price1,double price2, double price3);
}
