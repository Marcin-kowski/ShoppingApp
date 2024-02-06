package com.mrcinkowski.ShoppingApp.service.implementations;

import com.mrcinkowski.ShoppingApp.api.dto.AddressBody;
import com.mrcinkowski.ShoppingApp.repository.AddressRepository;
import com.mrcinkowski.ShoppingApp.repository.entities.AddressEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import com.mrcinkowski.ShoppingApp.service.AddressService;
import com.mrcinkowski.ShoppingApp.service.mapper.AddressServiceMapper;
import com.mrcinkowski.ShoppingApp.service.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {


    private final AddressRepository addressRepository;
    private final AddressServiceMapper mapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressServiceMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public AddressEntity addAddress(LocalUserEntity user, AddressBody addressBody) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1(addressBody.getAddressLine1());
        addressEntity.setAddressLine2(addressBody.getAddressLine2());
        addressEntity.setCity(addressBody.getCity());
        addressEntity.setCountry(addressBody.getCountry());
        addressEntity.setZipCode(addressBody.getZipCode());
        addressEntity.setUser(user);
        return addressRepository.save(addressEntity);
    }

    @Override
    public List<Address> getAllAddresses(LocalUserEntity user) {
        List<AddressEntity> listOfAddressesEntity = addressRepository.findByUser(user);
        return mapper.map(listOfAddressesEntity);
    }
}


