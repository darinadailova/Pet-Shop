package com.s14.petshop.service;

import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.repositories.AddressRepository;
import com.s14.petshop.model.repositories.ProductRepository;
import com.s14.petshop.model.repositories.UserRepository;
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

    void checkId(int id){
        if(id < 1){
            throw new BadRequestException("Id must be positive");
        }
    }
}
