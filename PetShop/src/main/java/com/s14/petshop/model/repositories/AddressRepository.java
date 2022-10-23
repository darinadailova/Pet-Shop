package com.s14.petshop.model.repositories;

import com.s14.petshop.model.beans.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findAllByOwnerId(int owner_id);
}
