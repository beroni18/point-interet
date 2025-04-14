package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.ContactDtoRequest;
import com.sji.group9.pointInteret.entity.Contact;
import com.sji.group9.pointInteret.repositorry.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    private ContactDtoRequest contactDtoRequest;
    private Contact contact;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contactDtoRequest = new ContactDtoRequest();
        contactDtoRequest.setEmail("john.doe@example.com");
        contactDtoRequest.setTelephone("123456789");
        contactDtoRequest.setNum_whatsapp("987654321");

        contact = new Contact();
        contact.setId(1L);
        contact.setEmail("john.doe@example.com");
        contact.setTelephone("123456789");
        contact.setNum_whatsapp("987654321");
    }

    @Test
    void testUpdate_Success() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact updatedContact = contactService.update(contactDtoRequest, 1L);

        assertNotNull(updatedContact);
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    void testUpdate_ContactNotFound() {
        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            contactService.update(contactDtoRequest, 1L);
        });

        assertEquals("Contact not found", exception.getMessage());
    }

    @Test
    void testGetById_Success() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        Contact foundContact = contactService.getById(1L);

        assertNotNull(foundContact);
        assertEquals(1L, foundContact.getId());
    }

    @Test
    void testGetById_NotFound() {
        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            contactService.getById(1L);
        });

        assertEquals("Contact not found", exception.getMessage());
    }

    @Test
    void testDeleteContact_Success() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        Contact deletedContact = contactService.deleteContact(1L);

        assertNotNull(deletedContact);
        verify(contactRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteContact_NotFound() {
        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            contactService.deleteContact(1L);
        });

        assertEquals("Contact not found", exception.getMessage());
    }
}