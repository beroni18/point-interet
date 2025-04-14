package com.sji.group9.pointInteret.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointInteretPlacedEvent {
    private Long id;
    private String libelle;
    private String category;
}
