package com.sji.group9.pointInteret.api;

import com.sji.group9.pointInteret.dto.AddressDtoRequest;
import com.sji.group9.pointInteret.entity.Address;
import com.sji.group9.pointInteret.repositorry.AddressRepository;
import com.sji.group9.pointInteret.serviceImpl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pt-interet/address")
public class AddressApi {
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private AddressRepository addressRepository;
    @GetMapping("/all")
    private ResponseEntity<List<Address>> getAllAddress(){
        return ResponseEntity.ok(addressService.getAll());
    }
    @GetMapping("/id/{id}")
    private ResponseEntity<Address> getById(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getById(id));
    }
    @PostMapping("/create")
    private ResponseEntity<Address> createAddress(@RequestBody AddressDtoRequest addressDtoRequest){
        return ResponseEntity.ok(addressService.create(addressDtoRequest));
    }
    @PostMapping("/save")
    private ResponseEntity<List<Address>> saveAddress(@RequestBody List<Address> addressList){
        return ResponseEntity.ok(addressRepository.saveAll(addressList));
    }
    @PatchMapping("/update/{id}")
    private ResponseEntity<Address> updateAddress(@RequestBody AddressDtoRequest addressDtoRequest, @PathVariable Long id){
        return ResponseEntity.ok(addressService.update(addressDtoRequest, id));
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Address> deleteAddress(@PathVariable Long id){
        return ResponseEntity.ok(addressService.delete(id));
    }
    @PostMapping("/get/create")
    private ResponseEntity<Address> getOrCreate(@RequestBody AddressDtoRequest addressDtoRequest){
        return ResponseEntity.ok(addressService.getOrCrate(addressDtoRequest));
    }
}
