package com.s14.petshop.service;

import com.s14.petshop.model.beans.Subcategory;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.repositories.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubcategoryService extends AbstractService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;
    public Subcategory getById(int sid) {
        return subcategoryRepository.findById(sid).orElseThrow(() -> new NotFoundException("Subcategory does not exist!"));
    }
}
