package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.AddressDtoRequest;
import com.sji.group9.pointInteret.entity.Address;
import com.sji.group9.pointInteret.repositorry.AddressRepository;
import com.sji.group9.pointInteret.service.AddressServiceFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressServiceFace {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address create(AddressDtoRequest addressDtoRequest) {
        Address address = new Address();
        address.setQuartier(addressDtoRequest.getQuartier());
        address.setVille(addressDtoRequest.getVille());
        address.setPointRepere(addressDtoRequest.getPoint_repere());
        return addressRepository.save(address);
    }
    @Override
    public Address getOrCrate(AddressDtoRequest addressDtoRequest){
        Optional<Address> addressOptional = addressRepository.findAddressByVilleIgnoreCaseAndQuartierIgnoreCaseAndPointRepereIgnoreCase(addressDtoRequest.getVille(), addressDtoRequest.getQuartier(), addressDtoRequest.getPoint_repere());
        if (addressOptional.isPresent()) return addressOptional.get();
        return create(addressDtoRequest);
    }
    @Override
    public Address update(AddressDtoRequest addressDtoRequest, Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            Address lastAdd = optionalAddress.get();
            lastAdd.setQuartier(addressDtoRequest.getQuartier());
            lastAdd.setVille(addressDtoRequest.getVille());
            lastAdd.setPointRepere(addressDtoRequest.getPoint_repere());
            return addressRepository.save(lastAdd);
        }
        throw new RuntimeException("Address not found");
    }

    @Override
    public Address delete(Long id) {
        Optional<Address> add = addressRepository.findById(id);
        if (add.isPresent()) {
            addressRepository.deleteById(id);
            return add.get();
        }
        else {
             return null;
        }
    }

    @Override
    public Address getById(Long id) {
        Optional<Address> add = addressRepository.findById(id);
        if (add.isPresent()) {
            return add.get();
        }
        return null;
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
