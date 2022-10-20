package com.s14.petshop.model.repositories;

import com.s14.petshop.model.beans.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
     Discount getById(int id);
}
