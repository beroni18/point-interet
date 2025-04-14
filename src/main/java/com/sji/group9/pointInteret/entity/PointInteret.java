package com.sji.group9.pointInteret.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class PointInteret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nom;
    @Column
    private String description;
    @Column
    private double latitude;
    @Column
    private double longitude;
    @Column
    private Boolean statut;
    @Column
    private Boolean isVerify;
    @Column
    private Boolean isReservable;
    @Column
    private Boolean isOpen;
    @Column
    private String emailUser;
    @Column
    private Long etoile;

    @ManyToOne(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            },
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "category_id")
    public Categorie category;
    @OneToMany(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REMOVE
            },
            fetch = FetchType.LAZY
    )
    public List<ServiceCentreInteret> serviceCentreInterets;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Contact> contacts;

    @ManyToOne(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            },
            fetch = FetchType.EAGER
    )
    public Address address;
}
