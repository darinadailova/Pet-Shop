package com.s14.petshop.model.repositories;

import com.s14.petshop.model.beans.Address;
import com.s14.petshop.model.beans.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
