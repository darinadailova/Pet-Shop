package com.s14.petshop.model.repositories;

import com.s14.petshop.model.beans.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
