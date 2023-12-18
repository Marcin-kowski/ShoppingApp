package com.mrcinkowski.ShoppingApp.api.controller.address;

import com.mrcinkowski.ShoppingApp.api.dto.AddressBody;
import com.mrcinkowski.ShoppingApp.exception.ProductAlreadyExistsException;
import com.mrcinkowski.ShoppingApp.model.Address;
import com.mrcinkowski.ShoppingApp.model.LocalUser;
import com.mrcinkowski.ShoppingApp.model.repository.AddressRepository;
import com.mrcinkowski.ShoppingApp.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    private final AddressRepository addressRepository;

    public AddressController(AddressService addressService,
                             AddressRepository addressRepository) {
        this.addressService = addressService;
        this.addressRepository = addressRepository;
    }

    @PostMapping()
    public ResponseEntity<Address> addAddress(@AuthenticationPrincipal LocalUser user,
                                              @Valid @RequestBody AddressBody addressBody) {
        addressService.addAddress(user, addressBody);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<Address>> getAllAddresses(@AuthenticationPrincipal LocalUser user) {
        return new ResponseEntity<>(addressService.getAllAddresses(user), HttpStatus.OK);
    }
}
