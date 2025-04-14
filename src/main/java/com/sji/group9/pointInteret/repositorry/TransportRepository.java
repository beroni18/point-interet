package com.sji.group9.pointInteret.repositorry;

import com.sji.group9.pointInteret.entity.Transport;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Observed
public interface TransportRepository extends JpaRepository<Transport, Long> {
    List<Transport> findByNom(String nom);
    List<Transport> findByStatut(Boolean statut);
    List<Transport> findByIsOpen(Boolean is_open);
    List<Transport> findByIsVerify(Boolean is_verify);
    List<Transport> findByIsReservable(Boolean is_reservable);
    List<Transport> findByCategory_Libelle(String libelle);
    List<Transport> findByAddress_Ville(String ville);
    List<Transport> findByAddress_Quartier(String quartier);
    List<Transport> findByAddress_PointRepere(String ptRepere);
}
