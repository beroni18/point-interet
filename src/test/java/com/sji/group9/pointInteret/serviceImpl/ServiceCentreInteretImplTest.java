package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.ServiceCentreInteretDtoRequest;
import com.sji.group9.pointInteret.entity.PointInteret;
import com.sji.group9.pointInteret.entity.ServiceCentreInteret;
import com.sji.group9.pointInteret.repositorry.PointInteretRepository;
import com.sji.group9.pointInteret.repositorry.ServiceCentreInteretRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceCentreInteretImplTest {

    @Mock
    private ServiceCentreInteretRepository serviceCentreInteretRepository;

    @Mock
    private PointInteretRepository pointInteretRepository;

    @InjectMocks
    private ServiceCentreInteretImpl serviceCentreInteretService;

    private ServiceCentreInteret serviceCentreInteret;
    private PointInteret pointInteret;

    @BeforeEach
    void setUp() {
        serviceCentreInteret = new ServiceCentreInteret();
        serviceCentreInteret.setId(1L);
        serviceCentreInteret.setLibelle("Piscine");
        serviceCentreInteret.setStatut(true);

        pointInteret = new PointInteret();
        pointInteret.setId(1L);
    }

    @Test
    void testSave() {
        ServiceCentreInteretDtoRequest dtoRequest = new ServiceCentreInteretDtoRequest();
        when(serviceCentreInteretRepository.save(any(ServiceCentreInteret.class))).thenReturn(serviceCentreInteret);
        when(pointInteretRepository.findById(1L)).thenReturn(Optional.of(pointInteret));

        ServiceCentreInteret result = serviceCentreInteretService.save(dtoRequest, 1L);

        assertNotNull(result);
        assertEquals("Piscine", result.getLibelle());
        verify(serviceCentreInteretRepository, times(1)).save(any(ServiceCentreInteret.class));
        verify(pointInteretRepository, times(1)).save(any(PointInteret.class));
    }

    @Test
    void testFindByLibelle() {
        when(serviceCentreInteretRepository.findServiceCentreInteretByLibelle("Piscine"))
                .thenReturn(Arrays.asList(serviceCentreInteret));

        List<ServiceCentreInteret> results = serviceCentreInteretService.findByLibelle("Piscine");

        assertFalse(results.isEmpty());
        assertEquals("Piscine", results.get(0).getLibelle());
    }

    @Test
    void testFindByStatut() {
        when(serviceCentreInteretRepository.findServiceCentreInteretByStatut(true))
                .thenReturn(Arrays.asList(serviceCentreInteret));

        List<ServiceCentreInteret> results = serviceCentreInteretService.findByStatut(true);

        assertFalse(results.isEmpty());
        assertTrue(results.get(0).getStatut());
    }

    @Test
    void testChangeStatut() {
        when(serviceCentreInteretRepository.findById(1L)).thenReturn(Optional.of(serviceCentreInteret));

        Boolean newStatut = serviceCentreInteretService.changeStatut(1L);

        assertFalse(newStatut);
        verify(serviceCentreInteretRepository, times(1)).save(any(ServiceCentreInteret.class));
    }

    @Test
    void testUpdate() {
        ServiceCentreInteretDtoRequest dtoRequest = new ServiceCentreInteretDtoRequest();
        when(serviceCentreInteretRepository.findById(1L)).thenReturn(Optional.of(serviceCentreInteret));
        when(serviceCentreInteretRepository.save(any(ServiceCentreInteret.class))).thenReturn(serviceCentreInteret);

        ServiceCentreInteret result = serviceCentreInteretService.update(dtoRequest, 1L);

        assertNotNull(result);
        assertEquals(serviceCentreInteret.getLibelle(), result.getLibelle());
    }

    @Test
    void testGetAll() {
        when(serviceCentreInteretRepository.findAll()).thenReturn(Arrays.asList(serviceCentreInteret));

        List<ServiceCentreInteret> results = serviceCentreInteretService.getAll();

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
    }

    @Test
    void testGetById() {
        when(serviceCentreInteretRepository.findById(1L)).thenReturn(Optional.of(serviceCentreInteret));

        ServiceCentreInteret result = serviceCentreInteretService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testDelete() {
        when(serviceCentreInteretRepository.findById(1L)).thenReturn(Optional.of(serviceCentreInteret));

        ServiceCentreInteret result = serviceCentreInteretService.delete(1L);

        assertNotNull(result);
        verify(serviceCentreInteretRepository, times(1)).deleteById(1L);
    }
}
