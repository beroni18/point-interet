package com.sji.group9.pointInteret.repositorry;

import com.sji.group9.pointInteret.entity.Address;
import com.sji.group9.pointInteret.entity.Categorie;
import com.sji.group9.pointInteret.entity.PointInteret;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Observed
public interface PointInteretRepository extends JpaRepository<PointInteret, Long> {
    List<PointInteret> findByNom(String nom);
    List<PointInteret> findByStatut(Boolean statut);
    List<PointInteret> findByIsOpen(Boolean is_open);
    List<PointInteret> findByIsVerify(Boolean is_verify);
    List<PointInteret> findByIsReservable(Boolean is_reservable);
    List<PointInteret> findByCategory_Libelle(String libelle);
    List<PointInteret> findByAddress_Ville(String ville);
    List<PointInteret> findByAddress_Quartier(String quartier);
    List<PointInteret> findByAddress_PointRepere(String ptRepere);
    List<PointInteret> findByEmailUser(String email);
}
