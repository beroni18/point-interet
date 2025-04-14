package com.sji.group9.pointInteret.dto;

import com.sji.group9.pointInteret.entity.ServiceCentreInteret;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceCentreInteretDtoRequest {
    private String libelle;
    private String description;
    private Long prix;
    @Lob
    private String image;
    private Boolean statut;
    public ServiceCentreInteret mapToServiceCentreInteret(){
        ServiceCentreInteret serviceCentreInteret = new ServiceCentreInteret();
        serviceCentreInteret.setDescription(this.getDescription());
        serviceCentreInteret.setPrix(this.getPrix());
        serviceCentreInteret.setLibelle(this.getLibelle());
        serviceCentreInteret.setImage(this.getImage());
        serviceCentreInteret.setStatut(this.getStatut());
        return serviceCentreInteret;
    }
}
