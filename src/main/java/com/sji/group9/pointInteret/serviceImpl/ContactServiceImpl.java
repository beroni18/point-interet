package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.ContactDtoRequest;
import com.sji.group9.pointInteret.entity.Contact;
import com.sji.group9.pointInteret.repositorry.ContactRepository;
import com.sji.group9.pointInteret.service.ContactServiceFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactServiceFace {
    @Autowired
    private ContactRepository contactRepository;
    @Override
    public Contact create(ContactDtoRequest contactDtoRequest) {
        return null;
    }

    @Override
    public List<Contact> findByName(String name) {
        return null;
    }

    @Override
    public Contact update(ContactDtoRequest contactDtoRequest, Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isPresent()){
            Contact contact = contactOptional.get();
            contact.setEmail(contactDtoRequest.getEmail());
            contact.setUrl(contactDtoRequest.getUrl());
            contact.setTelephone(contactDtoRequest.getTelephone());
            contact.setNum_whatsapp(contactDtoRequest.getNum_whatsapp());
            return contactRepository.save(contact);
        }
        throw new RuntimeException("Contact not found");
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getById(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()){
            return contact.get();
        }
        throw new RuntimeException("Contact not found");
    }

    @Override
    public Contact deleteContact(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()){
            contactRepository.deleteById(id);
            return contact.get();
        }
        throw new RuntimeException("Contact not found");
    }
}
