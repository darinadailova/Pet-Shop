package com.s14.petshop.service;

import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.dtos.product.ProductAddDTO;
import com.s14.petshop.model.dtos.product.ProductDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private SubcategoryService subcategoryService;

    public ProductDTO getById(int pid) {
        Product product = productRepository.findById(pid).orElseThrow(() -> new NotFoundException("Product not found!"));
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        return dto;
    }

    public ProductDTO addProduct(ProductAddDTO dto) {
        Product product = productRepository.findByName(dto.getName());
        if (product != null) {
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);
            ProductDTO dtoResult = modelMapper.map(product, ProductDTO.class);
            return dtoResult;
        }
        product = modelMapper.map(dto, Product.class);
        product.setBrand(brandService.getById(dto.getBrand_id()));
        product.setDiscount(discountService.getById(dto.getDiscount_id()));
        product.setSubcategory(subcategoryService.getById(dto.getSubcategory_id()));
        product.setQuantity(1);
        productRepository.save(product);

        ProductDTO dtoResult = modelMapper.map(product, ProductDTO.class);
        return dtoResult;
    }

    public boolean deleteProduct(int pid) {
        Product product = productRepository.findById(pid)
                .orElseThrow(() -> new NotFoundException("Product does not exist!"));
        if (product.getQuantity() > 0) {
            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
            return true;
        }
        throw new BadRequestException("Product is already out of stock");
    }

    public ProductDTO searchWithName(String dto) {
        if (dto == null || dto.isEmpty()) {
            throw new BadRequestException("Enter name for searching");
        }
        Product product = productRepository.findByName(dto);
        ProductDTO dtoResult = modelMapper.map(product, ProductDTO.class);
        return dtoResult;
    }

    public ProductDTO addDiscountToProduct(int pid, int did) {
        Product product = productRepository.findById(pid).
                orElseThrow(() -> new NotFoundException("The product does not exist"));

        product.setDiscount(discountService.getById(did));
        productRepository.save(product);

        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        return dto;
    }
}
