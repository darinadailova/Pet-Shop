package com.s14.petshop.service;

import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.dtos.product.ProductAddDTO;
import com.s14.petshop.model.dtos.product.ProductResponseDTO;
import com.s14.petshop.model.dtos.product.ProductEditDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private SubcategoryService subcategoryService;

    public ProductResponseDTO getById(int pid) {
        checkId(pid);
        Product product = productRepository.findById(pid).orElseThrow(() -> new NotFoundException("Product not found!"));
        ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);
        return dto;
    }

    public ProductResponseDTO addProduct(ProductAddDTO dto) {
        Product product = productRepository.findByName(dto.getName()).orElse(new Product());
        if (product.getQuantity() > 0) {
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);
            ProductResponseDTO dtoResult = modelMapper.map(product, ProductResponseDTO.class);
            return dtoResult;
        }
        product = createProduct(dto);

        productRepository.save(product);

        ProductResponseDTO dtoResult = modelMapper.map(product, ProductResponseDTO.class);
        return dtoResult;
    }

    private Product createProduct(ProductAddDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setInfo(dto.getInfo());
        product.setPrice(dto.getPrice());
        product.setBrand(brandService.getById(dto.getBrand_id()));
        product.setDiscount(discountService.getAllDiscountById(dto.getDiscount_id()));
        product.setSubcategory(subcategoryService.getAllSubById(dto.getSubcategory_id()));
        product.setQuantity(1);

        return product;
    }

    public ProductResponseDTO deleteProduct(int pid) {
        checkId(pid);
        Product product = productRepository.findById(pid)
                .orElseThrow(() -> new NotFoundException("Product does not exist!"));

        ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);

        productRepository.delete(product);

        return dto;
    }

    public ProductResponseDTO searchWithName(String dto) {
        if (dto == null || dto.isEmpty()) {
            throw new BadRequestException("Enter name for searching");
        }
        Product product = productRepository.findByNameIsLikeIgnoreCase(dto)
                .orElseThrow(() -> new NotFoundException("Product does not exist"));
        ProductResponseDTO dtoResult = modelMapper.map(product, ProductResponseDTO.class);
        return dtoResult;
    }

    public ProductResponseDTO addDiscountToProduct(int pid, int did) {
        checkId(pid);
        checkId(did);
        Product product = productRepository.findById(pid).
                orElseThrow(() -> new NotFoundException("The product does not exist"));

        product.setDiscount(discountService.getAllDiscountById(did));
        productRepository.save(product);

        ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);
        return dto;
    }


    public ProductResponseDTO editProduct(ProductEditDTO dto, int pid) {
        checkId(pid);
        Product product = productRepository.findById(pid)
                .orElseThrow(() -> new NotFoundException("Product does not exist"));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setInfo(dto.getInfo());

        productRepository.save(product);

        ProductResponseDTO result = modelMapper.map(product, ProductResponseDTO.class);
        return result;
    }
}