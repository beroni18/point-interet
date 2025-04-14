package com.sji.group9.pointInteret.serviceImpl;

import com.sji.group9.pointInteret.dto.TransportDtoRequest;
import com.sji.group9.pointInteret.entity.Transport;
import com.sji.group9.pointInteret.repositorry.AddressRepository;
import com.sji.group9.pointInteret.repositorry.CategorieRepository;
import com.sji.group9.pointInteret.repositorry.TransportRepository;
import com.sji.group9.pointInteret.service.TransportServiceFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportServiceImpl implements TransportServiceFace {
    @Autowired
    private TransportRepository transportRepository;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    AddressRepository addressRepository;
    @Override
    public List<Transport> getAll() {
        return transportRepository.findAll();
    }

    @Override
    public Transport save(TransportDtoRequest transportDtoRequest) {
        Transport transport = transportDtoRequest.mapToTransport();
        transport.setCategory(categorieRepository.findById(transportDtoRequest.getId_category()).get());
        transport.setAddress(addressRepository.findById(transportDtoRequest.getId_address()).get());
        return transportRepository.save(transport);
    }

    @Override
    public Transport update(TransportDtoRequest transportDtoRequest, Long id) {
        Optional<Transport> optionalTransport = transportRepository.findById(id);
        if (optionalTransport.isPresent()){
            Transport transport = optionalTransport.get();
            transport.setDescription(transportDtoRequest.getDescription());
            transport.setLatitude(transportDtoRequest.getLatitude());
            transport.setLongitude(transportDtoRequest.getLongitude());
            transport.setNom(transportDtoRequest.getNom());
            transport.setPrix_min(transportDtoRequest.getPrix_min());
            transport.setType(transportDtoRequest.getType());
            transport.setIsOpen(transportDtoRequest.getIs_open());
            transport.setIsVerify(transportDtoRequest.getIs_verify());
            transport.setIsReservable(transportDtoRequest.getIs_reservable());
            transport.setStatut(transportDtoRequest.getStatut());
            transportRepository.save(transport);
            return transport;
        }
        throw new RuntimeException("Transport not found");
    }


    @Override
    public List<Transport> findByName(String name) {
        return transportRepository.findByNom(name);
    }

    @Override
    public List<Transport> findByCategory(String libelle_category) {
        return transportRepository.findByCategory_Libelle(libelle_category);
    }

    @Override
    public List<Transport> getByStatut(Boolean statut) {
        return transportRepository.findByStatut(statut);
    }

    @Override
    public Boolean changeStatut(Long id) {
        Optional<Transport> optionalTransport = transportRepository.findById(id);
        if (optionalTransport.isPresent()){
            Transport transport = optionalTransport.get();
            transport.setStatut(!transport.getStatut());
            transportRepository.save(transport);
            return transport.getStatut();
        }
        throw new RuntimeException("Transport not found");
    }

    @Override
    public Boolean changeStatutReservable(Long id) {
        Transport hotel = transportRepository.findById(id).get();
        if (hotel != null){
            hotel.setIsReservable(!hotel.getIsReservable());
            transportRepository.save(hotel);
            return hotel.getIsReservable();
        }
        throw new RuntimeException("Transport not found");
    }

    @Override
    public List<Transport> getByStatutVerify(Boolean statut) {
        return transportRepository.findByStatut(statut);
    }

    @Override
    public List<Transport> getByStatutReservable(Boolean is_reservable) {
        return transportRepository.findByIsReservable(is_reservable);
    }

    @Override
    public Boolean changeStatutVerify(Long id) {
        Transport transport = transportRepository.findById(id).get();
        if (transport != null){
            transport.setIsVerify(!transport.getIsVerify());
            transportRepository.save(transport);
            return transport.getIsVerify();
        }
        throw new RuntimeException("Transport not found");
    }
    @Override
    public List<Transport> getProche(Long latitude, Long longitude) {
        return null;
    }

    @Override
    public Transport getById(Long id) {
        Optional<Transport> transportOptional = transportRepository.findById(id);
        if (transportOptional.isPresent()){
            return transportOptional.get();
        }
        throw new RuntimeException("Transport not found with id: "+id);
    }

    @Override
    public Transport delete(Long id) {
        Transport transport = getById(id);
        if (transport != null){
            transportRepository.deleteById(id);
            return transport;
        }
        else {
            throw new RuntimeException("Transport not found with id: "+id);
        }

    }
}
