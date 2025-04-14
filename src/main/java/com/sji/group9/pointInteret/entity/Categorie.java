package com.sji.group9.pointInteret.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String libelle;

    @OneToMany(mappedBy = "category") // Le "mappedBy" fait référence au champ "category" dans PointInteret
    @JsonIgnore
    private List<PointInteret> pointsInteret;

}
