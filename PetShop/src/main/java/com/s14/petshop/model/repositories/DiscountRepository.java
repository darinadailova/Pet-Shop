package com.s14.petshop.model.repositories;

import com.s14.petshop.model.beans.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    boolean existsByName(String name);

    Optional<Discount> findByName(String name);
}
