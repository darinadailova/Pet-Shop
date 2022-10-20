package com.s14.petshop.service;

import com.s14.petshop.model.beans.Discount;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.repositories.DiscountRepository;
import org.springframework.stereotype.Service;

@Service
public class DiscountService extends AbstractService {

    private DiscountRepository discountRepository;
    public Discount getById(int did) {
        if(!discountRepository.existsById(did)){
            throw new BadRequestException("Discount does not exist!");
        }
        return discountRepository.getById(did);
    }
}
