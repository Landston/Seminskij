package com.senla.model.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private UUID id;

    private String name;

    private String mail;

}
