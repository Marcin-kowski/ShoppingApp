package com.mrcinkowski.ShoppingApp.api.controller.address.mapper;

import com.mrcinkowski.ShoppingApp.api.controller.address.dto.AddressDto;
import com.mrcinkowski.ShoppingApp.service.model.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapper {


    public List<AddressDto> map(List<Address> addresses) {
        List<AddressDto> addressDtos = new ArrayList<>();
        addresses.forEach(address -> addressDtos.add(mapAddress(address)));
        return addressDtos;
    }

    private AddressDto mapAddress(Address address) {
        return AddressDto.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();
    }
}
