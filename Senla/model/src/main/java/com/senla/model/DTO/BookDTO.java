package com.senla.model.dto;

import com.senla.model.BookStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private UUID id;

    private String name;

    private BookStatus status;

    private String genre;

    private double cost;

    private int year;

    private LocalDate dateOfAdmission;


}
