package com.s14.petshop.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s14.petshop.model.beans.Image;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    boolean existsByImageUrl(String imageUrl);


}
