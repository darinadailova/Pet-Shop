package com.s14.petshop.service;

import com.s14.petshop.model.beans.Category;
import com.s14.petshop.model.dtos.category.CategoryResponseDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService extends AbstractService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponseDTO getById(int cid) {
        checkId(cid);
        Category category = categoryRepository.findById(cid)
                .orElseThrow(() -> new NotFoundException("Category does not exist!"));
        CategoryResponseDTO dto = modelMapper.map(category, CategoryResponseDTO.class);
        return dto;
    }

    public CategoryResponseDTO addCategory(String name) {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("Enter valid name");
        }
        if (categoryRepository.findByName(name).isPresent()) {
            throw new BadRequestException("Category already exists!");
        }
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);

        CategoryResponseDTO dto = modelMapper.map(category, CategoryResponseDTO.class);
        return dto;
    }

    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> result = new ArrayList<>();

        for (Category category : categories) {
            CategoryResponseDTO dto = modelMapper.map(category, CategoryResponseDTO.class);
            result.add(dto);
        }
        return result;
    }

    public CategoryResponseDTO deleteCategory(int cid) {
        checkId(cid);
        Category category = categoryRepository.findById(cid)
                .orElseThrow(() ->new NotFoundException("Subcategory cannot be deleted!"));
        CategoryResponseDTO dto = modelMapper.map(category, CategoryResponseDTO.class);
        categoryRepository.delete(category);
        return dto;
    }

    public Category getAllCategoryById(int cid) {
        checkId(cid);
        return categoryRepository.findById(cid)
                .orElseThrow(() -> new NotFoundException("Category does not exist!"));
    }
}