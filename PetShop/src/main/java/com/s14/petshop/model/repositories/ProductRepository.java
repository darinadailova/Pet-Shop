package com.s14.petshop.model.repositories;

import com.s14.petshop.model.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByNameIsLikeIgnoreCase(String name);
    Optional<Product> findProductById(int id);
    Optional<Product> findByName(String name);
}
