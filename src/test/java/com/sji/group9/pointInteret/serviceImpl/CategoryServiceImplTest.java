package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.CategoryDtoRequest;
import com.sji.group9.pointInteret.entity.Categorie;
import com.sji.group9.pointInteret.repositorry.CategorieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private CategoryDtoRequest categoryDtoRequest;
    private Categorie category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryDtoRequest = new CategoryDtoRequest();
        categoryDtoRequest.setLibelle("Test Category");

        category = new Categorie();
        category.setId(1L);
        category.setLibelle("Test Category");
    }

    @Test
    void testCreate_Success() {
        when(categorieRepository.save(any(Categorie.class))).thenReturn(category);

        Categorie createdCategory = categoryService.create(categoryDtoRequest);

        assertNotNull(createdCategory);
        assertEquals("Test Category", createdCategory.getLibelle());
        verify(categorieRepository, times(1)).save(any(Categorie.class));
    }

    @Test
    void testUpdate_Success() {
        when(categorieRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categorieRepository.save(any(Categorie.class))).thenReturn(category);

        Categorie updatedCategory = categoryService.update(categoryDtoRequest, 1L);

        assertNotNull(updatedCategory);
        assertEquals("Test Category", updatedCategory.getLibelle());
        verify(categorieRepository, times(1)).save(category);
    }

    @Test
    void testUpdate_CategoryNotFound() {
        when(categorieRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.update(categoryDtoRequest, 1L);
        });

        assertEquals("Category not found", exception.getMessage());
    }

    @Test
    void testDelete_Success() {
        when(categorieRepository.findById(1L)).thenReturn(Optional.of(category));

        Categorie deletedCategory = categoryService.delete(1L);

        assertNotNull(deletedCategory);
        verify(categorieRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_CategoryNotFound() {
        when(categorieRepository.findById(1L)).thenReturn(Optional.empty());

        Categorie deletedCategory = categoryService.delete(1L);

        assertNull(deletedCategory);
        verify(categorieRepository, times(0)).deleteById(1L);
    }

    @Test
    void testGetById_Success() {
        when(categorieRepository.findById(1L)).thenReturn(Optional.of(category));

        Categorie foundCategory = categoryService.getById(1L);

        assertNotNull(foundCategory);
        assertEquals(1L, foundCategory.getId());
    }

    @Test
    void testGetById_NotFound() {
        when(categorieRepository.findById(1L)).thenReturn(Optional.empty());

        Categorie foundCategory = categoryService.getById(1L);

        assertNull(foundCategory);
    }

    @Test
    void testGetOrCreate_CategoryExists() {
        when(categorieRepository.findByLibelleIgnoreCase("Test Category"))
                .thenReturn(Optional.of(category));

        Categorie retrievedCategory = categoryService.getOrCreate(categoryDtoRequest);

        assertNotNull(retrievedCategory);
        assertEquals("Test Category", retrievedCategory.getLibelle());
    }

    @Test
    void testGetOrCreate_CategoryNotExists() {
        when(categorieRepository.findByLibelleIgnoreCase("Test Category"))
                .thenReturn(Optional.empty());
        when(categorieRepository.save(any(Categorie.class))).thenReturn(category);

        Categorie createdCategory = categoryService.getOrCreate(categoryDtoRequest);

        assertNotNull(createdCategory);
        assertEquals("Test Category", createdCategory.getLibelle());
        verify(categorieRepository, times(1)).save(any(Categorie.class));
    }

    @Test
    void testGetAll() {
        when(categorieRepository.findAll()).thenReturn(List.of(category));

        List<Categorie> categories = categoryService.getAll();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals("Test Category", categories.get(0).getLibelle());
    }
}