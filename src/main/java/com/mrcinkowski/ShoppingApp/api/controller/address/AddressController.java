package com.mrcinkowski.ShoppingApp.api.controller.address;

import com.mrcinkowski.ShoppingApp.api.controller.address.dto.AddressDto;
import com.mrcinkowski.ShoppingApp.api.controller.address.mapper.AddressMapper;
import com.mrcinkowski.ShoppingApp.api.dto.AddressBody;
import com.mrcinkowski.ShoppingApp.repository.entities.AddressEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import com.mrcinkowski.ShoppingApp.service.AddressService;
import com.mrcinkowski.ShoppingApp.service.model.Address;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper mapper;


    @PostMapping()
    public ResponseEntity<AddressEntity> addAddress(@AuthenticationPrincipal LocalUserEntity user,
                                                    @Valid @RequestBody AddressBody addressBody) {
        addressService.addAddress(user, addressBody);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<AddressDto>> getAllAddresses(@AuthenticationPrincipal LocalUserEntity user) {
        List<Address> addresses = addressService.getAllAddresses(user);
        List<AddressDto> addressDtos = mapper.map(addresses);

        return new ResponseEntity<>(addressDtos, HttpStatus.OK);
    }
}
