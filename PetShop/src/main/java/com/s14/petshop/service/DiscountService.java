package com.s14.petshop.service;

import com.s14.petshop.model.beans.Discount;
import com.s14.petshop.model.dtos.discount.DiscountAddDTO;
import com.s14.petshop.model.dtos.discount.DiscountDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DiscountService extends AbstractService {

    @Autowired
    private DiscountRepository discountRepository;

    public DiscountDTO getById(int did) {
        Discount discount = getAllDiscountById(did);
        DiscountDTO dto = modelMapper.map(discount, DiscountDTO.class);
        return dto;
    }

    public DiscountDTO addDiscount(DiscountAddDTO dto) {
        if (discountRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Discount already exists!");
        }
        Discount discount = modelMapper.map(dto, Discount.class);
        discountRepository.save(discount);

        DiscountDTO resultDTO = modelMapper.map(discount, DiscountDTO.class);
        return resultDTO;
    }

    public boolean editDiscount(DiscountAddDTO dto, int did) {
        Discount discount = discountRepository
                .findById(did).orElseThrow(() -> new NotFoundException("Discount cannot be edit"));

        discount.setName(dto.getName());
        discount.setPercentDiscount(dto.getPercentDiscount());
        discount.setStartAt(dto.getStartAt());
        discount.setEndAt(dto.getEndAt());
        discountRepository.save(discount);
        return true;
    }

    public Discount getAllDiscountById(int did) {
        return discountRepository.findById(did)
                .orElseThrow(() -> new NotFoundException("Discount does not exist!"));
    }
}
