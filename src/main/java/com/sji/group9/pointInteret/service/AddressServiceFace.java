package com.sji.group9.pointInteret.service;

import com.sji.group9.pointInteret.dto.AddressDtoRequest;
import com.sji.group9.pointInteret.entity.Address;

import java.util.List;

public interface AddressServiceFace {
    Address create(AddressDtoRequest addressDtoRequest);
    Address update(AddressDtoRequest addressDtoRequest, Long id);
    Address delete(Long id);
    Address getById(Long id);
    List<Address> getAll();
    Address getOrCrate(AddressDtoRequest addressDtoRequest);
}
