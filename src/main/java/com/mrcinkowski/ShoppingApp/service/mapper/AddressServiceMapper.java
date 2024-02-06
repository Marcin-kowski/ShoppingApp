package com.mrcinkowski.ShoppingApp.service.mapper;

import com.mrcinkowski.ShoppingApp.repository.entities.AddressEntity;
import com.mrcinkowski.ShoppingApp.service.model.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressServiceMapper {

    public List<Address> map(List<AddressEntity> listOfAddressesEntity) {
        List<Address> addresses = new ArrayList<>();
        listOfAddressesEntity.forEach(addressEntity -> addresses.add(mapAddress(addressEntity)));
        return addresses;
    }

    private Address mapAddress(AddressEntity addressEntity) {
        return Address.builder()
                .addressLine2(addressEntity.getAddressLine2())
                .addressLine1(addressEntity.getAddressLine1())
                .city(addressEntity.getCity())
                .zipCode(addressEntity.getZipCode())
                .country(addressEntity.getCountry())
                .build();
    }
}
