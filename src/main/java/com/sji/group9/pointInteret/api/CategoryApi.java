package com.sji.group9.pointInteret.api;

import com.sji.group9.pointInteret.dto.CategoryDtoRequest;
import com.sji.group9.pointInteret.entity.Categorie;
import com.sji.group9.pointInteret.repositorry.CategorieRepository;
import com.sji.group9.pointInteret.serviceImpl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pt-interet/category")
public class CategoryApi {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private CategorieRepository categorieRepository;
    @GetMapping("/all")
    private ResponseEntity<List<Categorie>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAll());
    }
    @GetMapping("/id/{id}")
    private ResponseEntity<Categorie> getById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getById(id));
    }
    @PostMapping("/create")
    private ResponseEntity<Categorie> createCategoy(@RequestBody CategoryDtoRequest categoryDtoRequest){
        return ResponseEntity.ok(categoryService.create(categoryDtoRequest));
    }
    @PostMapping("/save")
    private ResponseEntity<List<Categorie>> createCategoy(@RequestBody List<Categorie> categories){
        return ResponseEntity.ok(categorieRepository.saveAll(categories));
    }
    @PatchMapping("/update/{id}")
    private ResponseEntity<Categorie> updateCategory(@RequestBody CategoryDtoRequest categoryDtoRequest, @PathVariable Long id){
        return ResponseEntity.ok(categoryService.update(categoryDtoRequest, id));
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Categorie> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.delete(id));
    }
    @DeleteMapping("/delete-multiple")
    public ResponseEntity<Void> deleteMultipleCategory(@RequestParam List<Long> ids) {
        categoryService.deleteMultipleCategory(ids);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/get/create")
    private ResponseEntity<Categorie> getOrCreate(@RequestBody CategoryDtoRequest categoryDtoRequest){
        return ResponseEntity.ok(categoryService.getOrCreate(categoryDtoRequest));
    }

}
