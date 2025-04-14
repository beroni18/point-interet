package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.PointInteretDtoRequest;
import com.sji.group9.pointInteret.entity.Categorie;
import com.sji.group9.pointInteret.entity.PointInteret;
import com.sji.group9.pointInteret.entity.Address;
import com.sji.group9.pointInteret.repositorry.AddressRepository;
import com.sji.group9.pointInteret.repositorry.CategorieRepository;
import com.sji.group9.pointInteret.repositorry.PointInteretRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PointInteretServiceImplTest {

    @Mock
    private PointInteretRepository pointInteretRepository;

    @Mock
    private CategorieRepository categorieRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private PointInteretServiceImpl pointInteretService;

    private PointInteretDtoRequest pointInteretDtoRequest;
    private PointInteret pointInteret;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pointInteretDtoRequest = new PointInteretDtoRequest();
        pointInteretDtoRequest.setId_category(1L);
        pointInteretDtoRequest.setId_address(1L);

        pointInteret = new PointInteret();
        pointInteret.setId(1L);
    }

    @Test
    void testSave_Success() {
        when(categorieRepository.findById(1L)).thenReturn(Optional.of(new Categorie()));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(new Address()));
        when(pointInteretRepository.save(any(PointInteret.class))).thenReturn(pointInteret);

        PointInteret savedPointInteret = pointInteretService.save(pointInteretDtoRequest);

        assertNotNull(savedPointInteret);
        verify(pointInteretRepository, times(1)).save(any(PointInteret.class));
    }

    @Test
    void testSave_CategoryNotFound() {
        when(categorieRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            pointInteretService.save(pointInteretDtoRequest);
        });

        assertEquals("Catégorie non trouvée", exception.getMessage());
    }

    @Test
    void testSave_AddressNotFound() {
        when(categorieRepository.findById(1L)).thenReturn(Optional.of(new Categorie()));
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            pointInteretService.save(pointInteretDtoRequest);
        });

        assertEquals("Adresse non trouvée", exception.getMessage());
    }

    @Test
    void testGetById_Success() {
        when(pointInteretRepository.findById(1L)).thenReturn(Optional.of(pointInteret));

        PointInteret foundPointInteret = pointInteretService.getById(1L);

        assertNotNull(foundPointInteret);
        assertEquals(1L, foundPointInteret.getId());
    }

    @Test
    void testGetById_NotFound() {
        when(pointInteretRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            pointInteretService.getById(1L);
        });

        assertEquals("Point d'interet not found with id: 1", exception.getMessage());
    }

    @Test
    void testDelete_Success() {
        // Arrange
        PointInteret pointInteret = new PointInteret();
        pointInteret.setId(1L);

        when(pointInteretRepository.existsById(1L)).thenReturn(true);
        when(pointInteretRepository.findById(1L)).thenReturn(Optional.of(pointInteret));

        // Act
        pointInteretService.delete(1L);

        // Assert
        verify(pointInteretRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(pointInteretRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            pointInteretService.delete(1L);
        });

        assertEquals("Point d'interet not found with id: 1", exception.getMessage());
    }
}