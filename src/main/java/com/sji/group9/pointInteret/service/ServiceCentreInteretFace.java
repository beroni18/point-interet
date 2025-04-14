package com.sji.group9.pointInteret.service;

import com.sji.group9.pointInteret.dto.ServiceCentreInteretDtoRequest;
import com.sji.group9.pointInteret.entity.ServiceCentreInteret;

import java.util.List;
import java.util.Optional;

public interface ServiceCentreInteretFace {
    ServiceCentreInteret save(ServiceCentreInteretDtoRequest serviceCentreInteretDtoRequest, Long id_pt_interet);
    List<ServiceCentreInteret> findByLibelle(String libelle);
    List<ServiceCentreInteret> findByStatut(Boolean statut);
    Boolean changeStatut(Long id);
    ServiceCentreInteret update(ServiceCentreInteretDtoRequest serviceCentreInteretDtoRequest, Long id);
    List<ServiceCentreInteret> getAll();
    ServiceCentreInteret getById(Long id);
    ServiceCentreInteret delete(Long id);
}
