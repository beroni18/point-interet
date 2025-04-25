package com.sji.group9.pointInteret.api;

import com.sji.group9.pointInteret.dto.CategoryDtoRequest;
import com.sji.group9.pointInteret.dto.ServiceCentreInteretDtoRequest;
import com.sji.group9.pointInteret.entity.Categorie;
import com.sji.group9.pointInteret.entity.ServiceCentreInteret;
import com.sji.group9.pointInteret.repositorry.ServiceCentreInteretRepository;
import com.sji.group9.pointInteret.serviceImpl.ServiceCentreInteretImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pt-interet/service")
public class ServiceCentreInteretApi {
    @Autowired
    private ServiceCentreInteretImpl serviceCentreInteret;

    @Autowired
    private ServiceCentreInteretRepository interetRepository;
    @GetMapping("/all")
    private ResponseEntity<List<ServiceCentreInteret>> getAllService(){
        return ResponseEntity.ok(serviceCentreInteret.getAll());
    }
    @GetMapping("/id/{id}")
    private ResponseEntity<ServiceCentreInteret> getById(@PathVariable Long id){
        return ResponseEntity.ok(serviceCentreInteret.getById(id));
    }
    @PostMapping("/create/{id_pt_interet}")
    private ResponseEntity<ServiceCentreInteret> createService(@RequestBody ServiceCentreInteretDtoRequest serviceCentreInteretDtoRequest, @PathVariable Long id_pt_interet){
        return ResponseEntity.ok(serviceCentreInteret.save(serviceCentreInteretDtoRequest, id_pt_interet));
    }
    @PostMapping("/create")
    private ResponseEntity<List<ServiceCentreInteret>> createService(@RequestBody List<ServiceCentreInteret> serviceCentreInterets){
        return ResponseEntity.ok(interetRepository.saveAll(serviceCentreInterets));
    }
    @PatchMapping("/update/{id}")
    private ResponseEntity<ServiceCentreInteret> updateService(@RequestBody ServiceCentreInteretDtoRequest serviceCentreInteretDtoRequest, @PathVariable Long id){
        return ResponseEntity.ok(serviceCentreInteret.update(serviceCentreInteretDtoRequest, id));
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ServiceCentreInteret> deleteService(@PathVariable Long id){
        return ResponseEntity.ok(serviceCentreInteret.delete(id));
    }
    @GetMapping("/statut/{statut}")
    private ResponseEntity<List<ServiceCentreInteret>> getByStatut(@PathVariable Boolean statut){
        return ResponseEntity.ok(serviceCentreInteret.findByStatut(statut));
    }
    @GetMapping("/libelle/{libelle}")
    private ResponseEntity<List<ServiceCentreInteret>> getByLibelle(@PathVariable String libelle){
        return ResponseEntity.ok(serviceCentreInteret.findByLibelle(libelle));
    }
    @GetMapping("/change-statut/{id}")
    private ResponseEntity<Boolean> changeSatut(@PathVariable Long id){
        return ResponseEntity.ok(serviceCentreInteret.changeStatut(id));
    }

}
