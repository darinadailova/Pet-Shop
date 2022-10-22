package com.s14.petshop.service;

import com.s14.petshop.model.beans.Address;
import com.s14.petshop.model.beans.User;
import com.s14.petshop.model.dtos.address.AddingAddress;
import com.s14.petshop.model.dtos.address.AddressWithoutOwnerDTO;
import com.s14.petshop.model.dtos.user.UserWithoutPassAndIsAdminDTO;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends AbstractService{
    public void addAddress(AddingAddress address, UserWithoutPassAndIsAdminDTO currentUser) {
        User user = userRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new NotFoundException("user not found!"));

        Address addressFroSavingInDb = modelMapper.map(address, Address.class);
        addressFroSavingInDb.setOwner(user);
        addressRepository.save(addressFroSavingInDb);
    }
}
