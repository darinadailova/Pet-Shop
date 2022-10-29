package com.s14.petshop.service;

import com.s14.petshop.model.beans.Discount;
import com.s14.petshop.model.dtos.discount.DiscountAddDTO;
import com.s14.petshop.model.dtos.discount.DiscountResponseDTO;
import com.s14.petshop.model.dtos.discount.DiscountWithProductsDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DiscountService extends AbstractService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    public DiscountWithProductsDTO getById(int did) {
        checkId(did);
        Discount discount = getAllDiscountById(did);
        DiscountWithProductsDTO dto = modelMapper.map(discount, DiscountWithProductsDTO.class);
        return dto;
    }

    public DiscountResponseDTO addDiscount(DiscountAddDTO dto) {
        if (discountRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Discount already exists!");
        }
        Discount discount = modelMapper.map(dto, Discount.class);
        discountRepository.save(discount);

        emailSenderService.sendEmailToAllSubscribedUsers(discount);

        DiscountResponseDTO resultDTO = modelMapper.map(discount, DiscountResponseDTO.class);
        return resultDTO;
    }

    public DiscountResponseDTO editDiscount(DiscountAddDTO dto, int did) {
        checkId(did);
        Discount discount = discountRepository
                .findById(did).orElseThrow(() -> new NotFoundException("Discount cannot be edit"));

        discount.setName(dto.getName());
        discount.setPercentDiscount(dto.getPercentDiscount());
        discount.setStartAt(dto.getStartAt());
        discount.setEndAt(dto.getEndAt());
        discountRepository.save(discount);
        return modelMapper.map(discount, DiscountResponseDTO.class);
    }

    public Discount getAllDiscountById(int did) {
        checkId(did);
        return discountRepository.findById(did)
                .orElseThrow(() -> new NotFoundException("Discount does not exist!"));
    }
}
