package com.s14.petshop.service;

import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.dtos.ProductDTO;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO getById(int pid) {
        Product product = productRepository.findById(pid).orElseThrow(() -> new NotFoundException("Product not found!"));
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        return dto;
    }


    public ProductDTO addProduct(ProductDTO dto) {
        if (productRepository.existsByName(dto.getName())) {
            Product product = productRepository.findByName(dto.getName());
            product.setQuantity(product.getQuantity() + 1);
        }
        Product product = modelMapper.map(dto, Product.class);
        product.setQuantity(1);

        productRepository.save(product);
        return dto;
    }
}
