package com.sji.group9.pointInteret.service;

import com.sji.group9.pointInteret.dto.TransportDtoRequest;
import com.sji.group9.pointInteret.entity.Transport;

import java.util.List;

public interface TransportServiceFace {
    List<Transport> getAll();
    Transport save(TransportDtoRequest transportDtoRequest);
    Transport update(TransportDtoRequest transportDtoRequest, Long id);
    List<Transport> findByName(String name);
    List<Transport> findByCategory(String category);
    Boolean changeStatut(Long id);
    Boolean changeStatutVerify(Long id);
    Boolean changeStatutReservable(Long id);
    List<Transport> getByStatutVerify(Boolean is_verify);
    List<Transport> getByStatutReservable(Boolean is_reservable);
    List<Transport> getByStatut(Boolean statut);
    List<Transport> getProche(Long latitude, Long longitude);
    Transport getById(Long id);
    Transport delete(Long id);
}
