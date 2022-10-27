package com.s14.petshop.service;

import com.s14.petshop.model.beans.Brand;
import com.s14.petshop.model.dtos.brand.BrandAddDTO;
import com.s14.petshop.model.dtos.brand.BrandResponseDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.repositories.BrandRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class BrandService extends AbstractService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand getById(int bid) {
        checkId(bid);
        return brandRepository.findById(bid).orElseThrow(() -> new NotFoundException("Brand does not found"));
    }

    public BrandResponseDTO addBrand(MultipartFile file, String name) {
        if (brandRepository.existsByName(name)) {
            throw new BadRequestException("Brand already exist!");
        }
        try {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            if (!checkImageExtension(extension)) {
                throw new BadRequestException("Insert an image");
            }

            String fileName = "uploads" + File.separator + System.nanoTime() + "-" + name + "." + extension;
            File file2 = new File(fileName);

            copyFile(file, file2);

            Brand brand = new Brand();
            brand.setName(name);
            brand.setLogoUrl(fileName);
            brandRepository.save(brand);

            BrandResponseDTO result = modelMapper.map(brand, BrandResponseDTO.class);
            return result;
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }
}