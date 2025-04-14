package com.sji.group9.pointInteret.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String ville;
    @Column
    private String quartier;
    @Column
    private String pointRepere;
}
