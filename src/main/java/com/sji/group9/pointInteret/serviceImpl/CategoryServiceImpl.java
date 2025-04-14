package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.AddressDtoRequest;
import com.sji.group9.pointInteret.dto.CategoryDtoRequest;
import com.sji.group9.pointInteret.entity.Address;
import com.sji.group9.pointInteret.entity.Categorie;
import com.sji.group9.pointInteret.repositorry.CategorieRepository;
import com.sji.group9.pointInteret.service.CategorieServiceFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategorieServiceFace {
    @Autowired
    private CategorieRepository categorieRepository;
    @Override
    public Categorie create(CategoryDtoRequest categoryDtoRequest) {
        Categorie categorie = new Categorie();
        categorie.setLibelle(categoryDtoRequest.getLibelle());
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie update(CategoryDtoRequest categoryDtoRequest, Long id) {
        Optional<Categorie> optionalCategorie= categorieRepository.findById(id);
        if (optionalCategorie.isPresent()){
            Categorie lastCat = optionalCategorie.get();
            lastCat.setLibelle(categoryDtoRequest.getLibelle());
            return categorieRepository.save(lastCat);
        }
        throw new RuntimeException("Category not found");
    }

    @Override
    public Categorie delete(Long id) {
        Optional<Categorie> cat = categorieRepository.findById(id);
        if (cat.isPresent()) {
            categorieRepository.deleteById(id);
            return cat.get();
        }
        else {
            return null;
        }
    }

    @Override
    public Categorie getById(Long id) {
        Optional<Categorie> cat = categorieRepository.findById(id);
        if (cat.isPresent()) {
            return cat.get();
        }
        else {
            return null;
        }
    }

    @Override
    public Categorie getOrCreate(CategoryDtoRequest categoryDtoRequest){
        Optional<Categorie> categorieOptional = categorieRepository.findByLibelleIgnoreCase(categoryDtoRequest.getLibelle());
        return categorieOptional.orElseGet(() -> create(categoryDtoRequest));
    }

    @Override
    public List<Categorie> getAll() {
        return categorieRepository.findAll();
    }
    @Override
    public void deleteMultipleCategory(List<Long> ids) {
        categorieRepository.deleteAllById(ids);
    }
}
