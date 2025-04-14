package com.sji.group9.pointInteret.dto;

import com.sji.group9.pointInteret.entity.Transport;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransportDtoRequest {
    private String nom;
    private String description;
    private Long latitude;
    private Long longitude;
    private String type;
    private Long prix_min;
    private Boolean statut;
    private Boolean is_verify;
    private Boolean is_reservable;
    private Boolean is_open;
    private Long id_address;
    private Long id_category;
    private String emailUser;

    public Transport mapToTransport(){
        Transport transport = new Transport();
        transport.setDescription(this.getDescription());
        transport.setLatitude(this.getLatitude());
        transport.setLongitude(this.getLongitude());
        transport.setNom(this.getNom());
        transport.setType(this.getType());
        transport.setPrix_min(this.getPrix_min());
        transport.setIsOpen(this.getIs_open());
        transport.setIsVerify(this.getIs_verify());
        transport.setIsReservable(this.getIs_reservable());
        transport.setStatut(this.getStatut());
        transport.setEmailUser(this.getEmailUser());
        return transport;
    }

}
