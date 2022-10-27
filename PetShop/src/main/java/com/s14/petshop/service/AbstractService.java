package com.s14.petshop.service;

import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService {

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected AddressRepository addressRepository;
    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected ReviewRepository reviewRepository;

    @Autowired
    protected ImageRepository imageRepository;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected ProductQuantityRepository productQuantityRepository;

     boolean checkImageExtension(String extension) {
        extension = extension.toLowerCase();
        return (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")
                || extension.equals("gif") || extension.equals("raw") || extension.equals("svg") ||
                extension.equals("heic"));

    }

    void checkId(int id){
        if(id < 1){
            throw new BadRequestException("Id must be positive");
        }
    }
}
