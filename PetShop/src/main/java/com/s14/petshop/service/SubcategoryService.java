package com.s14.petshop.service;

import com.s14.petshop.model.beans.Category;
import com.s14.petshop.model.beans.Subcategory;
import com.s14.petshop.model.dtos.category.CategoryResponseDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryAddDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryResponseDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.repositories.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryService extends AbstractService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryService categoryService;

    public SubcategoryResponseDTO getById(int sid) {
        checkId(sid);
        Subcategory subcategory = subcategoryRepository.findById(sid)
                .orElseThrow(() -> new NotFoundException("Subcategory does not exist!"));
        SubcategoryResponseDTO dto = modelMapper.map(subcategory, SubcategoryResponseDTO.class);
        return dto;
    }

    public SubcategoryResponseDTO addSubcategory(SubcategoryAddDTO dto) {
        if (subcategoryRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Subcategory already exists!");
        }
        Subcategory subcategory = new Subcategory();
        subcategory.setName(dto.getName());
        Category category = categoryService.getAllCategoryById(dto.getCategoryId());
        subcategory.setCategory(category);
        subcategoryRepository.save(subcategory);

        SubcategoryResponseDTO resultDTO = modelMapper.map(subcategory, SubcategoryResponseDTO.class);
        return resultDTO;
    }

    public Subcategory getAllSubById(int sid) {
        checkId(sid);
        return subcategoryRepository.findById(sid)
                .orElseThrow(() -> new NotFoundException("Subcategory does not exist!"));
    }

    public List<CategoryResponseDTO> getAllSubcategories() {
        return categoryService.getAllCategories();
    }

    public SubcategoryResponseDTO deleteSubcategory(int sid) {
        checkId(sid);
        Subcategory subcategory = subcategoryRepository.findById(sid)
                .orElseThrow(() ->  new NotFoundException("Subcategory cannot be deleted!"));
        SubcategoryResponseDTO dto = modelMapper.map(subcategory, SubcategoryResponseDTO.class);

        subcategoryRepository.delete(subcategory);
        return dto;
    }
}
