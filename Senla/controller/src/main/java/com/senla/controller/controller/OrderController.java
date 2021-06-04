package com.senla.controller.controller;

import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IOrderService;
import com.senla.model.dto.OrderDTO;
import com.senla.model.mapper.api.OrderMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    private final IOrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(IOrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping(value = "/{{id}}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable String id) throws ServiceException {
        OrderDTO dto = orderService.getOrderByID(UUID.fromString(id));

        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<OrderDTO>> getAllOrder(@RequestParam(required = false) String condition,
                                                      @RequestParam(defaultValue = "5") Integer limit,
                                                      @RequestParam(defaultValue = "1") Integer startPage){
    return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/add")
    public ResponseEntity<OrderDTO> createOrder(@RequestParam String bookId, @RequestParam String clientId) throws ServiceException {
        OrderDTO newOrderDto = orderService.createOrder(UUID.fromString(bookId), UUID.fromString(clientId));

        return ResponseEntity.ok(newOrderDto);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<OrderDTO> deleteOrder(@RequestParam String orderId) throws ServiceException {
        OrderDTO dto = orderService.delete(UUID.fromString(orderId));

        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO dto) throws ServiceException {
        OrderDTO updatedOrderDto = orderService.update(dto);

        return ResponseEntity.ok(updatedOrderDto);
    }
}
