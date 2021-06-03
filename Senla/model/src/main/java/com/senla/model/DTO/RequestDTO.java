package com.senla.model.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private UUID id;
    private BookDTO requestedBooks;
    private int count = 1;
    private boolean requestOpenClose;

    public RequestDTO(BookDTO toDto) {
        requestedBooks  = toDto;
        requestOpenClose = true;
    }
}
