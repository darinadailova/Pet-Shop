package com.s14.petshop.service;

import com.s14.petshop.model.beans.Category;
import com.s14.petshop.model.dtos.category.CategoryDTO;
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

    public CategoryDTO getById(int cid) {
        Category category = categoryRepository.findById(cid)
                .orElseThrow(() -> new NotFoundException("Category does not exist!"));
        CategoryDTO dto = modelMapper.map(category,CategoryDTO.class);
        return dto;
    }

    public boolean addCategory(String name) {
        if(categoryRepository.findByName(name).isPresent()){
           throw  new BadRequestException("Category already exists!");
        }
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return true;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> result = new ArrayList<>();

        for (Category category : categories) {
            if(category.getName().equals("deleted")){
                continue;
            }
            CategoryDTO dto = modelMapper.map(category,CategoryDTO.class);
            result.add(dto);
        }
        return result;
    }

    public boolean deleteCategory(int cid) {
        Category category = categoryRepository.findById(cid)
                .orElseThrow(() -> new NotFoundException("Category can not be deleted!"));

        if(category.getName().equals("deleted")){
            throw new BadRequestException("Category is already deleted!");
        }
        category.setName("deleted");
        category.setSubcategoryList(null);
        categoryRepository.save(category);
        return true;
    }
}
