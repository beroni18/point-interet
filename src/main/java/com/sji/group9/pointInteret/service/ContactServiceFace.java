package com.sji.group9.pointInteret.service;

import com.sji.group9.pointInteret.dto.ContactDtoRequest;
import com.sji.group9.pointInteret.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactServiceFace {
    Contact create(ContactDtoRequest contactDtoRequest);
    List<Contact> findByName(String name);
    Contact update(ContactDtoRequest contactDtoRequest, Long id);
    List<Contact> getAll();
    Contact getById(Long id);
    Contact deleteContact(Long id);
}
