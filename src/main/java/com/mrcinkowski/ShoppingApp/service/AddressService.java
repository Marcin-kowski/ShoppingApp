package com.mrcinkowski.ShoppingApp.service;

import com.mrcinkowski.ShoppingApp.api.dto.AddressBody;
import com.mrcinkowski.ShoppingApp.repository.entities.AddressEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import com.mrcinkowski.ShoppingApp.service.model.Address;

import java.util.List;

public interface AddressService {

    AddressEntity addAddress(LocalUserEntity user, AddressBody addressBody);

    List<Address> getAllAddresses(LocalUserEntity user);

}
