package com.senla.model.dto;

import com.senla.model.OrderStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private UUID id;
    private List<BookDTO> booksToOrder;
    private ClientDTO client;
    private OrderStatus status;
    private double totalPrice;
    private LocalDate dateOfExecution;
}
