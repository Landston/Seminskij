package com.senla.model.DTO;

import com.senla.model.BookStatus;
import com.senla.model.Order;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class BookDTO {

    private UUID id;

    private String name;

    private BookStatus status;

    private String genre;

    private double cost;

    private int year;

    private LocalDate dateOfAdmission;


}
