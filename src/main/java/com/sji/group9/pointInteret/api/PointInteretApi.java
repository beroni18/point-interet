package com.sji.group9.pointInteret.api;

import com.sji.group9.pointInteret.dto.PointInteretDtoRequest;
import com.sji.group9.pointInteret.entity.PointInteret;
import com.sji.group9.pointInteret.repositorry.PointInteretRepository;
import com.sji.group9.pointInteret.serviceImpl.PointInteretServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pt-interet")
public class PointInteretApi {
    @Autowired
    private PointInteretServiceImpl pointInteretService;
    @Autowired
    private PointInteretRepository pointInteretRepository;

    @GetMapping("/all")
    private ResponseEntity<List<PointInteret>> getAllPointInteret(){
        return ResponseEntity.ok(pointInteretService.getAll());
    }

    @GetMapping("/all/{emailUser}")
    private ResponseEntity<List<PointInteret>> getAllPointInteretByEmailUser(@PathVariable String emailUser){
        return ResponseEntity.ok(pointInteretService.getByEmailUser(emailUser));
    }

    @GetMapping("/{id}")
    private PointInteret getById(@PathVariable Long id){
        return pointInteretService.getById(id);
    }

    @PostMapping("/save")
    private ResponseEntity<PointInteret> save(@RequestBody @Valid PointInteretDtoRequest pointInteretDtoRequest){
        PointInteret savedPointInteret = pointInteretService.save(pointInteretDtoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPointInteret.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/create")
    private ResponseEntity<List<PointInteret>> create(@RequestBody List<PointInteret> pointInteretList){
        List<PointInteret> savedPointInteret = pointInteretRepository.saveAll(pointInteretList);

        return ResponseEntity.ok(savedPointInteret);
    }

    @GetMapping("/delete/{id}")
    private void deletePointInteret(@PathVariable Long id){
        pointInteretService.delete(id);
    }

    @PatchMapping("/update/{id}")
    private ResponseEntity<PointInteret> updatePointInteret(@RequestBody @Valid PointInteretDtoRequest pointInteretDtoRequest, @PathVariable Long id){
        return ResponseEntity.ok(pointInteretService.update(pointInteretDtoRequest, id));
    }
    @GetMapping("/change-statut/{id}")
    private Boolean changeSatut(@PathVariable Long id){
        return pointInteretService.changeStatut(id);
    }

    @GetMapping("/change-verify/{id}")
    private Boolean changeIs_verify(@PathVariable Long id){
        return pointInteretService.changeStatutVerify(id);
    }

    @GetMapping("/change-reservable/{id}")
    private Boolean changeIs_reservable(@PathVariable Long id){
        return pointInteretService.changeStatutReservable(id);
    }

    @GetMapping("/get-is-reservable/{statut}")
    private List<PointInteret> getIs_reservable(@PathVariable Boolean statut){
        return pointInteretService.getByStatutReservable(statut);
    }

    @GetMapping("/get-is-verify/{statut}")
    private List<PointInteret> getIs_verify(@PathVariable Boolean statut){
        return pointInteretService.getByStatutVerify(statut);
    }

    @GetMapping("/get-is-statut/{statut}")
    private List<PointInteret> changeIs_reservable(@PathVariable Boolean statut){
        return pointInteretService.getByStatut(statut);
    }

    @GetMapping("/name/{name}")
    private ResponseEntity<List<PointInteret>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(pointInteretService.findByName(name));
    }

    @GetMapping("/category/{libelle_category}")
    private ResponseEntity<List<PointInteret>> findByCategory(@PathVariable String libelle_category){
        return ResponseEntity.ok(pointInteretService.findByCategory(libelle_category));
    }
    @DeleteMapping("/delete-multiple")
    public ResponseEntity<Void> deleteMultiplePtInteret(@RequestParam List<Long> ids) {
        pointInteretService.deleteMultiplePtInteret(ids);
        return ResponseEntity.noContent().build();
    }
}
