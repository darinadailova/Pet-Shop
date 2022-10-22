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

        Address addressFroSavingInDb = new Address();
        addressFroSavingInDb.setCity(address.getCity());
        addressFroSavingInDb.setStreetAddress(address.getStreetAddress());
        addressFroSavingInDb.setApartmentBuilding(address.getApartmentBuilding());
        addressFroSavingInDb.setPostcode(address.getPostcode());
        addressFroSavingInDb.setApartmentNumber(address.getApartmentNumber());
        addressFroSavingInDb.setOwner(user);

        currentUser.getAddresses().add(modelMapper.map(address, AddressWithoutOwnerDTO.class));
        addressRepository.save(addressFroSavingInDb);
    }
}
