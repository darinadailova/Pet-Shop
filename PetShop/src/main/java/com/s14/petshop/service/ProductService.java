package com.s14.petshop.service;

import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.dtos.products.ProductDTO;
import com.s14.petshop.model.dtos.products.ProductNameDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService extends AbstractService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DiscountService discountService;

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

    public boolean deleteProduct(int pid) {
        if(!productRepository.existsById(pid)){
            throw new BadRequestException("This product does not exist!");
        }
        Product product = productRepository.findById(pid).get();
        product.setQuantity(product.getQuantity() - 1);
        productRepository.save(product);
        return true;
    }

    public ProductDTO searchWithName(ProductNameDTO dto) {
        if(dto.getName() == null || dto.getName().isEmpty()){
            throw new BadRequestException("Enter name for searching");
        }

        Product product = productRepository.findByName(dto.getName());
        ProductDTO dtoResult = modelMapper.map(product, ProductDTO.class);
        return dtoResult;
    }

    public ProductDTO addDiscountToProduct(int pid, int did) {
        if(!productRepository.existsById(pid)){
            throw new BadRequestException("The product does not exist");
        }

        Product product = productRepository.findById(pid).get();

        product.setDiscount(discountService.getById(did));
        productRepository.save(product);

        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        return dto;
    }
}
