package com.sji.group9.pointInteret.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String url;
    @Column
    private String email;
    @Column
    private String telephone;
    @Column
    private String num_whatsapp;
    public Contact(Long id, String url, String email, String telephone, String num_whatsapp) {
        this.id = id;
        this.url = url;
        this.email = email;
        this.telephone = telephone;
        this.num_whatsapp = num_whatsapp;
    }
}
