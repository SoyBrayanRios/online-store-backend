package com.app.store.dao;

import com.app.store.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "select * from address where main_address=:mainAddress and complement=:complement", nativeQuery = true)
    public Address findByMainAddressComplement(String mainAddress, String complement);

}
