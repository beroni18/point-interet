package com.sji.group9.pointInteret.repositorry;

import com.sji.group9.pointInteret.entity.ServiceCentreInteret;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Observed
public interface ServiceCentreInteretRepository extends JpaRepository<ServiceCentreInteret, Long> {
    List<ServiceCentreInteret> findServiceCentreInteretByLibelle(String libelle);
    List<ServiceCentreInteret> findServiceCentreInteretByStatut(Boolean statut);
}
