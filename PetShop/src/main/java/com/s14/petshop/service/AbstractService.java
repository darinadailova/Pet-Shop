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
    protected ProductQuantityRepository productQuantityRepository;

    @Autowired
    protected OrderRepository orderRepository;

    void checkId(int id){
        if(id < 1){
            throw new BadRequestException("Id must be positive");
        }
    }
}
