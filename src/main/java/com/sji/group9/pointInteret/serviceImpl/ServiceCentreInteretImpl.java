package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.ServiceCentreInteretDtoRequest;
import com.sji.group9.pointInteret.entity.Contact;
import com.sji.group9.pointInteret.entity.PointInteret;
import com.sji.group9.pointInteret.entity.ServiceCentreInteret;
import com.sji.group9.pointInteret.repositorry.PointInteretRepository;
import com.sji.group9.pointInteret.repositorry.ServiceCentreInteretRepository;
import com.sji.group9.pointInteret.service.ServiceCentreInteretFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCentreInteretImpl implements ServiceCentreInteretFace {
    @Autowired
    private ServiceCentreInteretRepository serviceCentreInteretRepository;
    @Autowired
    PointInteretRepository pointInteretRepository;
    @Override
    public ServiceCentreInteret save(ServiceCentreInteretDtoRequest serviceCentreInteretDtoRequest, Long id_pt_interet) {
        ServiceCentreInteret serviceCentreInteret = serviceCentreInteretDtoRequest.mapToServiceCentreInteret();
        serviceCentreInteret = serviceCentreInteretRepository.save(serviceCentreInteret);
        PointInteret pointInteret = pointInteretRepository.findById(id_pt_interet)
                .orElseThrow(() -> new RuntimeException("Point d'intérêt non trouvé"));

        if (pointInteret.getServiceCentreInterets() == null) {
            pointInteret.setServiceCentreInterets(new ArrayList<>());
        }
        pointInteret.serviceCentreInterets.add(serviceCentreInteret);
        pointInteretRepository.save(pointInteret);
        return serviceCentreInteret;
    }

    @Override
    public List<ServiceCentreInteret> findByLibelle(String libelle) {
        return serviceCentreInteretRepository.findServiceCentreInteretByLibelle(libelle);
    }

    @Override
    public List<ServiceCentreInteret> findByStatut(Boolean statut) {
        return serviceCentreInteretRepository.findServiceCentreInteretByStatut(statut);
    }

    @Override
    public Boolean changeStatut(Long id) {
        Optional<ServiceCentreInteret> serviceCentreInteret = serviceCentreInteretRepository.findById(id);
        if (serviceCentreInteret.isPresent()){
            ServiceCentreInteret serviceCentreInteret1 = serviceCentreInteret.get();
            serviceCentreInteret1.setStatut(!serviceCentreInteret.get().getStatut());
            serviceCentreInteretRepository.save(serviceCentreInteret1);
            return serviceCentreInteret1.getStatut();
        }
        throw new RuntimeException("Service not found");
    }

    @Override
    public ServiceCentreInteret update(ServiceCentreInteretDtoRequest serviceCentreInteretDtoRequest, Long id) {
        Optional<ServiceCentreInteret> serviceCentreInteret = serviceCentreInteretRepository.findById(id);
        if (serviceCentreInteret.isPresent()){
            ServiceCentreInteret serviceCentreInteret1 = serviceCentreInteret.get();
            serviceCentreInteret1.setStatut(serviceCentreInteret.get().getStatut());
            serviceCentreInteret1.setDescription(serviceCentreInteret.get().getDescription());
            serviceCentreInteret1.setPrix(serviceCentreInteret.get().getPrix());
            serviceCentreInteret1.setImage(serviceCentreInteret.get().getImage());
            serviceCentreInteret1.setLibelle(serviceCentreInteret.get().getLibelle());
            return serviceCentreInteretRepository.save(serviceCentreInteret1);
        }
        throw new RuntimeException("Service not found");
    }

    @Override
    public List<ServiceCentreInteret> getAll() {
        return serviceCentreInteretRepository.findAll();
    }

    @Override
    public ServiceCentreInteret getById(Long id) {
        Optional<ServiceCentreInteret> serviceCentreInteret = serviceCentreInteretRepository.findById(id);
        if (serviceCentreInteret.isPresent()){
            return serviceCentreInteret.get();
        }
        throw new RuntimeException("Auccun service trouve avec l'id: "+id);
    }

    @Override
    public ServiceCentreInteret delete(Long id) {
        Optional<ServiceCentreInteret> serviceCentreInteret = serviceCentreInteretRepository.findById(id);
        if (serviceCentreInteret.isPresent()){
            serviceCentreInteretRepository.deleteById(id);
            return serviceCentreInteret.get();
        }
        else {
            throw new RuntimeException("Auccun service trouve avec l'id: "+id);
        }
    }
}
