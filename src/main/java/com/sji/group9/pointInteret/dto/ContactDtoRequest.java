package com.sji.group9.pointInteret.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactDtoRequest {
    private String url;
    @Email
    private String email;
    private String telephone;
    private String num_whatsapp;
}
