package com.sji.group9.pointInteret.service;

import com.sji.group9.pointInteret.dto.CategoryDtoRequest;
import com.sji.group9.pointInteret.entity.Categorie;

import java.util.List;

public interface CategorieServiceFace {
    Categorie create(CategoryDtoRequest categoryDtoRequest);
    Categorie update(CategoryDtoRequest categoryDtoRequest, Long id);
    Categorie delete(Long id);
    Categorie getById(Long id);
    List<Categorie> getAll();
    Categorie getOrCreate(CategoryDtoRequest categoryDtoRequest);
    void deleteMultipleCategory(List<Long> ids);

}
