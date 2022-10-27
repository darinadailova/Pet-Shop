package com.s14.petshop.service;

import com.s14.petshop.model.beans.Address;
import com.s14.petshop.model.beans.User;
import com.s14.petshop.model.dtos.address.AddingAddress;
import com.s14.petshop.model.dtos.address.AddressWithOwnerIdDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService extends AbstractService{
    public AddressWithOwnerIdDTO addAddress(AddingAddress address, int currentUserId) {
        User user = userRepository.getById(currentUserId)
                .orElseThrow(() -> new NotFoundException("user not found!"));

        Address addressFroSavingInDb = modelMapper.map(address, Address.class);
        addressFroSavingInDb.setOwner(user);
        if (isAddressAlreadyAddedToUser(addressFroSavingInDb, user)) {
            throw new BadRequestException("This address is already added!");
        }
        addressRepository.save(addressFroSavingInDb);
        return modelMapper.map(addressFroSavingInDb, AddressWithOwnerIdDTO.class);
    }

    private boolean isAddressAlreadyAddedToUser(Address address, User user) {
        List<Address> addresses = addressRepository.findAllByOwnerId(user.getId());
        for (Address x : addresses) {
            if (x.equals(address)) {
                return true;
            }
        }
        return false;
    }

    public List<AddingAddress> getAllAddressesByUserId(int uid) {
        User user = userRepository.getById(uid)
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<Address> addresses = addressRepository.findAllByOwnerId(uid);
        return addresses.stream()
        .map(a -> modelMapper.map(a, AddingAddress.class))
        .collect(Collectors.toList());
    }
}
