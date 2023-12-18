package com.mrcinkowski.ShoppingApp.service;

import com.mrcinkowski.ShoppingApp.api.dto.AddressBody;
import com.mrcinkowski.ShoppingApp.model.Address;
import com.mrcinkowski.ShoppingApp.model.LocalUser;
import com.mrcinkowski.ShoppingApp.model.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address addAddress(LocalUser user, AddressBody addressBody) {
        Address address = new Address();
        address.setAddressLine1(addressBody.getAddressLine1());
        address.setAddressLine2(addressBody.getAddressLine2());
        address.setCity(addressBody.getCity());
        address.setCountry(addressBody.getCountry());
        address.setZipCode(addressBody.getZipCode());
        address.setUser(user);
        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses(LocalUser user) {
        return addressRepository.findByUser(user);
    }
}


