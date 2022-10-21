package com.s14.petshop.model.repositories;

import com.s14.petshop.model.beans.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
}
