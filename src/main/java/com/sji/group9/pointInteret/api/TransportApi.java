package com.sji.group9.pointInteret.api;

import com.sji.group9.pointInteret.dto.TransportDtoRequest;
import com.sji.group9.pointInteret.entity.PointInteret;
import com.sji.group9.pointInteret.entity.Transport;
import com.sji.group9.pointInteret.serviceImpl.TransportServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pt-interet/transport")
public class TransportApi {

    @Autowired
    private TransportServiceImpl transportService;

    @GetMapping("/all")
    private ResponseEntity<List<Transport>> getAllTransport(){
        return ResponseEntity.ok(transportService.getAll());
    }

    @GetMapping("/{id}")
    private Transport getById(@PathVariable Long id){
        return transportService.getById(id);
    }

    @PostMapping("/save")
    private ResponseEntity<Transport> save(@RequestBody @Valid TransportDtoRequest transportDtoRequest){
        PointInteret savedPointInteret = transportService.save(transportDtoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPointInteret.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/delete/{id}")
    private void deleteTransport(@PathVariable Long id){
        transportService.delete(id);
    }

    @PatchMapping("/update/{id}")
    private ResponseEntity<Transport> updateTransport(@RequestBody @Valid TransportDtoRequest transportDtoRequest, @PathVariable Long id){
        return ResponseEntity.ok(transportService.update(transportDtoRequest, id));
    }
    @GetMapping("/change-statut/{id}")
    private Boolean changeSatut(@PathVariable Long id){
        return transportService.changeStatut(id);
    }
    @GetMapping("/change-verify/{id}")
    private Boolean changeIs_verify(@PathVariable Long id){
        return transportService.changeStatutVerify(id);
    }
    @GetMapping("/change-reservable/{id}")
    private Boolean changeIs_reservable(@PathVariable Long id){
        return transportService.changeStatutReservable(id);
    }
    @GetMapping("/get-is-reservable/{statut}")
    private List<Transport> getIs_reservable(@PathVariable Boolean statut){
        return transportService.getByStatutReservable(statut);
    }
    @GetMapping("/get-is-verify/{statut}")
    private List<Transport> getIs_verify(@PathVariable Boolean statut){
        return transportService.getByStatutVerify(statut);
    }
    @GetMapping("/get-is-statut/{statut}")
    private List<Transport> changeIs_reservable(@PathVariable Boolean statut){
        return transportService.getByStatut(statut);
    }
    @GetMapping("/name/{name}")
    private ResponseEntity<List<Transport>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(transportService.findByName(name));
    }
    @GetMapping("/category/{libelle_category}")
    private ResponseEntity<List<Transport>> findByCategory(@PathVariable String libelle_category){
        return ResponseEntity.ok(transportService.findByCategory(libelle_category));
    }
}
