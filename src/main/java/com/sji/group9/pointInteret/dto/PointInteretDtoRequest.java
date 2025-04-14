package com.sji.group9.pointInteret.dto;

import com.sji.group9.pointInteret.entity.PointInteret;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointInteretDtoRequest {
    private String nom;
    private String description;
    private Long latitude;
    private Long longitude;
    private Boolean statut;
    private Long id_category;
    private Boolean is_verify;
    private Boolean is_reservable;
    private Boolean is_open;
    private Long id_address;
    private String emailUser;
    private Long etoile;

    public PointInteret mapToPointInteret(){
        PointInteret pointInteret = new PointInteret();
        pointInteret.setDescription(this.getDescription());
        pointInteret.setLatitude(this.getLatitude());
        pointInteret.setLongitude(this.getLongitude());
        pointInteret.setNom(this.getNom());
        pointInteret.setIsOpen(this.getIs_open());
        pointInteret.setIsVerify(this.getIs_verify());
        pointInteret.setIsReservable(this.getIs_reservable());
        pointInteret.setStatut(this.getStatut());
        pointInteret.setEmailUser(this.getEmailUser());
        pointInteret.setEtoile(this.getEtoile());
        return pointInteret;
    }
}
