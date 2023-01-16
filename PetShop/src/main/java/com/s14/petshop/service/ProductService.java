package com.s14.petshop.service;

import com.s14.petshop.model.beans.Image;
import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.dao.ProductFilterDAO;
import com.s14.petshop.model.dtos.product.ProductAddDTO;
import com.s14.petshop.model.dtos.product.ProductFilterDTO;
import com.s14.petshop.model.dtos.product.ProductResponseDTO;
import com.s14.petshop.model.dtos.product.ProductEditDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ImageService imageService;

    @Autowired
    ProductFilterDAO dao;

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
            System.out.println(product.getPrice());
            product = productRepository.save(product);
            System.out.println(product.getPrice());
            ProductResponseDTO dtoResult = modelMapper.map(product, ProductResponseDTO.class);
            System.out.println(dtoResult.getPrice());
            return dtoResult;
        }
        product = createProduct(dto);

        productRepository.save(product);

        ProductResponseDTO dtoResult = modelMapper.map(product, ProductResponseDTO.class);
        return dtoResult;
    }

    private Product createProduct(ProductAddDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setInfo(dto.getInfo());
        product.setPrice(dto.getPrice());
        product.setBrand(brandService.getById(dto.getBrand_id()));
        if(dto.getDiscount_id() > 0) {
            product.setDiscount(discountService.getAllDiscountById(dto.getDiscount_id()));
        }
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

    public List<ProductResponseDTO> searchWithName(String name) {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("Enter name for searching");
        }
        List<Product> products = productRepository.findAllByNameIsLikeIgnoreCase("%" + name + "%");

        List<ProductResponseDTO> result = new ArrayList<>();
        for (Product product : products) {
            result.add(modelMapper.map(product,ProductResponseDTO.class));
        }

        return result;
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

    public List<ProductResponseDTO> filterProducts(ProductFilterDTO dto) {
        List<Integer> productsIds = dao.filterProducts(dto);

        List<ProductResponseDTO> products = new ArrayList<>();

        for (Integer productsId : productsIds) {
            products.add(getById(productsId));
        }
        return products;
    }

    public ProductResponseDTO uploadImage(MultipartFile file, int pid) {
        checkId(pid);
        Product product = productRepository.findById(pid)
                .orElseThrow(() -> new NotFoundException("Product does not exist!"));
        Image image = imageService.addImage(file, product);

        ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);
        return dto;
    }
}