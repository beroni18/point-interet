package com.sji.group9.pointInteret.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Transport extends PointInteret{
    @Column
    private String type;
    @Column
    private Long prix_min;

    public Transport(String type, Long prix_min) {
        super();
        this.type = type;
        this.prix_min = prix_min;
    }
}
