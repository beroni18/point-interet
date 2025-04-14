package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.PointInteretDtoRequest;
import com.sji.group9.pointInteret.entity.PointInteret;
import com.sji.group9.pointInteret.event.PointInteretPlacedEvent;
import com.sji.group9.pointInteret.repositorry.AddressRepository;
import com.sji.group9.pointInteret.repositorry.CategorieRepository;
import com.sji.group9.pointInteret.repositorry.PointInteretRepository;
import com.sji.group9.pointInteret.service.PointInteretServiceFace;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointInteretServiceImpl implements PointInteretServiceFace {
    @Autowired
    PointInteretRepository pointInteretRepository;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    KafkaTemplate<String, PointInteretPlacedEvent> kafkaTemplate;
    @Override
    public PointInteret save(PointInteretDtoRequest pointInteretDtoRequest) {
        // Map du DTO vers l'entité PointInteret
        PointInteret pointInteret = pointInteretDtoRequest.mapToPointInteret();

        // Vérification de l'ID de la catégorie
        Long categoryId = pointInteretDtoRequest.getId_category();
        if (categoryId == null) {
            throw new IllegalArgumentException("L'ID de la catégorie ne peut pas être null");
        }

        // Recherche de la catégorie et affectation à l'objet PointInteret
        pointInteret.setCategory(categorieRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée")));

        // Vérification de l'ID de l'adresse
        Long addressId = pointInteretDtoRequest.getId_address();
        if (addressId == null) {
            throw new IllegalArgumentException("L'ID de l'adresse ne peut pas être null");
        }

        // Recherche de l'adresse et affectation à l'objet PointInteret
        pointInteret.setAddress(addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Adresse non trouvée")));

        // Sauvegarde du PointInteret
        return pointInteretRepository.save(pointInteret);
    }

    @Override
    public List<PointInteret> findByName(String name) {
        return pointInteretRepository.findByNom(name);
    }

    @Override
    public List<PointInteret> findByCategory(String libelle_category) {
        return pointInteretRepository.findByCategory_Libelle(libelle_category);
    }

    @Override
    public List<PointInteret> getByStatut(Boolean statut) {
        return pointInteretRepository.findByStatut(statut);
    }

    @Override
    public List<PointInteret> getByCategory(String libelle_category) {
        return pointInteretRepository.findByCategory_Libelle(libelle_category);
    }

    @Override
    public Boolean changeStatut(Long id) {
        Optional<PointInteret> optionalPointInteret = pointInteretRepository.findById(id);

        if (optionalPointInteret.isPresent()) {
            PointInteret pointInteret = optionalPointInteret.get();

            // Inverser le statut
            pointInteret.setStatut(!pointInteret.getStatut());

            // Sauvegarder les modifications
            pointInteretRepository.save(pointInteret);

            // Retourner le nouveau statut
            return pointInteret.getIsReservable();
        }
        else {
            // Gérer le cas où le PointInteret n'existe pas
            throw new RuntimeException("Point d'intérêt non trouvé avec l'ID : " + id);
        }
    }

    @Override
    public Boolean changeStatutReservable(Long id) {
        Optional<PointInteret> optionalPointInteret = pointInteretRepository.findById(id);

        if (optionalPointInteret.isPresent()) {
            PointInteret pointInteret = optionalPointInteret.get();

            // Inverser le statut isReservable
            pointInteret.setIsReservable(!pointInteret.getIsReservable());

            // Sauvegarder les modifications
            pointInteretRepository.save(pointInteret);

            // Retourner le nouveau statut
            return pointInteret.getIsReservable();
        }
        else {
            // Gérer le cas où le PointInteret n'existe pas
            throw new RuntimeException("Point d'intérêt non trouvé avec l'ID : " + id);
        }
    }

    @Override
    public List<PointInteret> getByStatutVerify(Boolean statut) {
        return pointInteretRepository.findByStatut(statut);
    }

    @Override
    public List<PointInteret> getByStatutReservable(Boolean is_reservable) {
        return pointInteretRepository.findByIsReservable(is_reservable);
    }

    @Override
    public Boolean changeStatutVerify(Long id) {
        Optional<PointInteret> optionalPointInteret = pointInteretRepository.findById(id);

        if (optionalPointInteret.isPresent()) {
            PointInteret pointInteret = optionalPointInteret.get();

            // Inverser le statut isVerify
            pointInteret.setIsVerify(!pointInteret.getIsVerify());

            // Sauvegarder les modifications
            pointInteretRepository.save(pointInteret);

            // Retourner le nouveau statut
            return pointInteret.getIsReservable();
        }
        else {
            // Gérer le cas où le PointInteret n'existe pas
            throw new RuntimeException("Point d'intérêt non trouvé avec l'ID : " + id);
        }
    }

    @Override
    public PointInteret update(PointInteretDtoRequest pointInteretDtoRequest, Long id) {
        Optional<PointInteret> optionalPointInteret = pointInteretRepository.findById(id);
        if (optionalPointInteret.isPresent()){
            PointInteret pointInteret = optionalPointInteret.get();
            pointInteret.setDescription(pointInteretDtoRequest.getDescription());
            pointInteret.setLatitude(pointInteretDtoRequest.getLatitude());
            pointInteret.setLongitude(pointInteretDtoRequest.getLongitude());
            pointInteret.setNom(pointInteretDtoRequest.getNom());
            pointInteret.setIsOpen(pointInteretDtoRequest.getIs_open());
            pointInteret.setIsVerify(pointInteretDtoRequest.getIs_verify());
            pointInteret.setIsReservable(pointInteretDtoRequest.getIs_reservable());
            pointInteret.setStatut(pointInteretDtoRequest.getStatut());

            pointInteretRepository.save(pointInteret);
            return pointInteret;
        }
        throw new RuntimeException("Point interet not found");
    }

    @Override
    public List<PointInteret> getAll() {
        return pointInteretRepository.findAll();
    }

    @Override
    public List<PointInteret> getProche(Long latitude, Long longitude) {
        return null;
    }

    @Override
    public PointInteret getById(Long id) {
        Optional<PointInteret> pointInteretOptional = pointInteretRepository.findById(id);
        if (pointInteretOptional.isPresent()){
            return pointInteretOptional.get();
        }
        throw new RuntimeException("Point d'interet not found with id: "+id);
    }

    @Override
    public List<PointInteret> getByEmailUser(String emailUser) {
        return pointInteretRepository.findByEmailUser(emailUser);
    }

    @Override
    public void delete(Long id) {
        if (getById(id) != null){
            pointInteretRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Point d'interet not found with id: "+id);
        }

    }
    @Override
    public void deleteMultiplePtInteret(List<Long> ids) {
        pointInteretRepository.deleteAllById(ids);
    }
}
