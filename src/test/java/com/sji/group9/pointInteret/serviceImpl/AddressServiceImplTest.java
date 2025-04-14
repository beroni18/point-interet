package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.AddressDtoRequest;
import com.sji.group9.pointInteret.entity.Address;
import com.sji.group9.pointInteret.repositorry.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    private AddressDtoRequest addressDtoRequest;
    private Address address;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addressDtoRequest = new AddressDtoRequest();
        addressDtoRequest.setQuartier("Test Quartier");
        addressDtoRequest.setVille("Test Ville");
        addressDtoRequest.setPoint_repere("Test Point");

        address = new Address();
        address.setId(1L);
        address.setQuartier("Test Quartier");
        address.setVille("Test Ville");
        address.setPointRepere("Test Point");
    }

    @Test
    void testCreate_Success() {
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address createdAddress = addressService.create(addressDtoRequest);

        assertNotNull(createdAddress);
        assertEquals("Test Quartier", createdAddress.getQuartier());
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void testUpdate_Success() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address updatedAddress = addressService.update(addressDtoRequest, 1L);

        assertNotNull(updatedAddress);
        assertEquals("Test Quartier", updatedAddress.getQuartier());
        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void testUpdate_AddressNotFound() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            addressService.update(addressDtoRequest, 1L);
        });

        assertEquals("Address not found", exception.getMessage());
    }

    @Test
    void testDelete_Success() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        Address deletedAddress = addressService.delete(1L);

        assertNotNull(deletedAddress);
        verify(addressRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_AddressNotFound() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        Address deletedAddress = addressService.delete(1L);

        assertNull(deletedAddress);
        verify(addressRepository, times(0)).deleteById(1L);
    }

    @Test
    void testGetById_Success() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        Address foundAddress = addressService.getById(1L);

        assertNotNull(foundAddress);
        assertEquals(1L, foundAddress.getId());
    }

    @Test
    void testGetById_NotFound() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        Address foundAddress = addressService.getById(1L);

        assertNull(foundAddress);
    }

    @Test
    void testGetOrCreate_ExistingAddress() {
        when(addressRepository.findAddressByVilleIgnoreCaseAndQuartierIgnoreCaseAndPointRepereIgnoreCase(
                "Test Ville", "Test Quartier", "Test Point"))
                .thenReturn(Optional.of(address));

        Address retrievedAddress = addressService.getOrCrate(addressDtoRequest);

        assertNotNull(retrievedAddress);
        assertEquals("Test Quartier", retrievedAddress.getQuartier());
    }

    @Test
    void testGetOrCreate_NewAddress() {
        when(addressRepository.findAddressByVilleIgnoreCaseAndQuartierIgnoreCaseAndPointRepereIgnoreCase(
                "Test Ville", "Test Quartier", "Test Point"))
                .thenReturn(Optional.empty());
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address createdAddress = addressService.getOrCrate(addressDtoRequest);

        assertNotNull(createdAddress);
        assertEquals("Test Quartier", createdAddress.getQuartier());
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void testGetAll() {
        when(addressRepository.findAll()).thenReturn(List.of(address));

        List<Address> addresses = addressService.getAll();

        assertNotNull(addresses);
        assertEquals(1, addresses.size());
        assertEquals("Test Quartier", addresses.get(0).getQuartier());
    }
}