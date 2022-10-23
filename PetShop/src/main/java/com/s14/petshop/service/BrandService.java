package com.s14.petshop.service;

import com.s14.petshop.model.beans.Brand;
import com.s14.petshop.model.dtos.brand.BrandAddDTO;
import com.s14.petshop.model.dtos.brand.BrandDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService extends AbstractService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand getById(int bid) {
       return brandRepository.findById(bid).orElseThrow(() -> new NotFoundException("Brand does not found"));
    }

    public BrandDTO addBrand(BrandAddDTO dto) {
        if(brandRepository.existsByName(dto.getName())){
            throw new BadRequestException("Brand already exist!");
        }

        Brand brand = modelMapper.map(dto,Brand.class);
        brandRepository.save(brand);

        BrandDTO result = modelMapper.map(brand, BrandDTO.class);
        return result;
    }
}
