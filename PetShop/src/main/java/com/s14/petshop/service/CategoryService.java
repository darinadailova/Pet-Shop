package com.s14.petshop.service;

import com.s14.petshop.model.beans.Category;
import com.s14.petshop.model.dtos.category.CategoryDTO;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO getById(int sid) {
        Category category = categoryRepository.findById(sid)
                .orElseThrow(() -> new NotFoundException("Subcategory does not exist!"));
        CategoryDTO dto = modelMapper.map(category,CategoryDTO.class);
        return dto;
    }
}
