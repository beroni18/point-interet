package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.TransportDtoRequest;
import com.sji.group9.pointInteret.entity.Address;
import com.sji.group9.pointInteret.entity.Categorie;
import com.sji.group9.pointInteret.entity.Transport;
import com.sji.group9.pointInteret.repositorry.AddressRepository;
import com.sji.group9.pointInteret.repositorry.CategorieRepository;
import com.sji.group9.pointInteret.repositorry.TransportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransportServiceImplTest {

    @Mock
    private TransportRepository transportRepository;

    @Mock
    private CategorieRepository categorieRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private TransportServiceImpl transportService;

    private TransportDtoRequest transportDtoRequest;
    private Transport transport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transportDtoRequest = new TransportDtoRequest();
        transportDtoRequest.setNom("Test Transport");
        transportDtoRequest.setDescription("Description");
        transportDtoRequest.setLatitude(10L);
        transportDtoRequest.setLongitude(20L);
        transportDtoRequest.setPrix_min(100L);
        transportDtoRequest.setType("Type");
        transportDtoRequest.setIs_open(true);
        transportDtoRequest.setIs_verify(true);
        transportDtoRequest.setIs_reservable(true);
        transportDtoRequest.setStatut(true);
        transportDtoRequest.setId_category(1L);
        transportDtoRequest.setId_address(1L);

        transport = new Transport();
        transport.setId(1L);
        transport.setNom("Test Transport");
        transport.setDescription("Description");
        transport.setLatitude(10L);
        transport.setLongitude(20L);
        transport.setPrix_min(100L);
        transport.setType("Type");
        transport.setIsOpen(true);
        transport.setIsVerify(true);
        transport.setIsReservable(true);
        transport.setStatut(true);
    }

    @Test
    void testSave_Success() {
        when(categorieRepository.findById(1L)).thenReturn(Optional.of(new Categorie()));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(new Address()));
        when(transportRepository.save(any(Transport.class))).thenReturn(transport);

        Transport savedTransport = transportService.save(transportDtoRequest);

        assertNotNull(savedTransport);
        assertEquals("Test Transport", savedTransport.getNom());
        verify(transportRepository, times(1)).save(any(Transport.class));
    }

    @Test
    void testUpdate_Success() {
        when(transportRepository.findById(1L)).thenReturn(Optional.of(transport));
        when(transportRepository.save(any(Transport.class))).thenReturn(transport);

        Transport updatedTransport = transportService.update(transportDtoRequest, 1L);

        assertNotNull(updatedTransport);
        assertEquals("Test Transport", updatedTransport.getNom());
        verify(transportRepository, times(1)).save(transport);
    }

    @Test
    void testUpdate_TransportNotFound() {
        when(transportRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            transportService.update(transportDtoRequest, 1L);
        });

        assertEquals("Transport not found", exception.getMessage());
    }

    @Test
    void testDelete_Success() {
        when(transportRepository.findById(1L)).thenReturn(Optional.of(transport));

        Transport deletedTransport = transportService.delete(1L);

        assertNotNull(deletedTransport);
        verify(transportRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_TransportNotFound() {
        when(transportRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            transportService.delete(1L);
        });

        assertEquals("Transport not found with id: 1", exception.getMessage());
    }

    @Test
    void testGetById_Success() {
        when(transportRepository.findById(1L)).thenReturn(Optional.of(transport));

        Transport foundTransport = transportService.getById(1L);

        assertNotNull(foundTransport);
        assertEquals(1L, foundTransport.getId());
    }

    @Test
    void testGetById_NotFound() {
        when(transportRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            transportService.getById(1L);
        });

        assertEquals("Transport not found with id: 1", exception.getMessage());
    }

    @Test
    void testChangeStatut_Success() {
        when(transportRepository.findById(1L)).thenReturn(Optional.of(transport));
        when(transportRepository.save(any(Transport.class))).thenReturn(transport);

        Boolean newStatut = transportService.changeStatut(1L);

        assertNotNull(newStatut);
        assertFalse(newStatut); // Changement de statut
        verify(transportRepository, times(1)).save(transport);
    }

    @Test
    void testChangeStatut_NotFound() {
        when(transportRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            transportService.changeStatut(1L);
        });

        assertEquals("Transport not found", exception.getMessage());
    }

    // Ajoutez d'autres tests pour les méthodes restantes...

    @Test
    void testGetAll() {
        when(transportRepository.findAll()).thenReturn(List.of(transport));

        List<Transport> transports = transportService.getAll();

        assertNotNull(transports);
        assertEquals(1, transports.size());
        assertEquals("Test Transport", transports.get(0).getNom());
    }
}