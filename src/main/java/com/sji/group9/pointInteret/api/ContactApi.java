package com.sji.group9.pointInteret.api;

import com.sji.group9.pointInteret.dto.AddressDtoRequest;
import com.sji.group9.pointInteret.dto.CategoryDtoRequest;
import com.sji.group9.pointInteret.dto.ContactDtoRequest;
import com.sji.group9.pointInteret.entity.Address;
import com.sji.group9.pointInteret.entity.Contact;
import com.sji.group9.pointInteret.repositorry.ContactRepository;
import com.sji.group9.pointInteret.serviceImpl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pt-interet/contact")
public class ContactApi {
    @Autowired
    private ContactServiceImpl contactService;
    @Autowired
    private ContactRepository contactRepository;
    @GetMapping("/all")
    private ResponseEntity<List<Contact>> getAllContact(){
        return ResponseEntity.ok(contactService.getAll());
    }
    @GetMapping("/id/{id}")
    private ResponseEntity<Contact> getById(@PathVariable Long id){
        return ResponseEntity.ok(contactService.getById(id));
    }
    @GetMapping("/name/{name}")
    private ResponseEntity<List<Contact>> findByNane(@PathVariable String name){
        return ResponseEntity.ok(contactService.findByName(name));
    }
    @PostMapping("/create")
    private ResponseEntity<Contact> createContact(@RequestBody ContactDtoRequest contactDtoRequest){
        return ResponseEntity.ok(contactService.create(contactDtoRequest));
    }
    @PostMapping("/saveAll")
    private ResponseEntity<List<Contact>> saveAddress(@RequestBody List<Contact> contacts){
        return ResponseEntity.ok(contactRepository.saveAll(contacts));
    }
    @PatchMapping("/update/{id}")
    private ResponseEntity<Contact> updateContact(@RequestBody ContactDtoRequest contactDtoRequest, @PathVariable Long id){
        return ResponseEntity.ok(contactService.update(contactDtoRequest, id));
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Contact> deleteContact(@PathVariable Long id){
        return ResponseEntity.ok(contactService.deleteContact(id));
    }
}
