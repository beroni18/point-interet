package com.sji.group9.pointInteret.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ServiceCentreInteret {
    @Id
    private Long id;
    @Column
    private String libelle;
    @Column
    private String description;
    @Column
    private Long prix;
    @Lob
    private String image;
    @Column
    private Boolean statut;

    public ServiceCentreInteret(String libelle, Boolean statut, String description, Long prix, String image) {
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.statut = statut;
    }
}