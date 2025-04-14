package com.sji.group9.pointInteret.service;

import com.sji.group9.pointInteret.dto.PointInteretDtoRequest;
import com.sji.group9.pointInteret.entity.PointInteret;

import java.util.List;
import java.util.Optional;

public interface PointInteretServiceFace {
    PointInteret save(PointInteretDtoRequest pointInteretDtoRequest);
    List<PointInteret> findByName(String name);
    List<PointInteret> findByCategory(String category);
    Boolean changeStatut(Long id);
    Boolean changeStatutVerify(Long id);
    Boolean changeStatutReservable(Long id);
    List<PointInteret> getByStatutVerify(Boolean is_verify);
    List<PointInteret> getByStatutReservable(Boolean is_reservable);
    List<PointInteret> getByStatut(Boolean statut);
    List<PointInteret> getByCategory(String libelle_category);
    PointInteret update(PointInteretDtoRequest pointInteretDtoRequest, Long id);
    List<PointInteret> getAll();
    List<PointInteret> getProche(Long latitude, Long longitude);
    PointInteret getById(Long id);
    List<PointInteret> getByEmailUser(String emailUser);
    void delete(Long id);
    void deleteMultiplePtInteret(List<Long> ids);
}
