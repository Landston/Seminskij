package com.senla.controller.controller;

import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IClientService;
import com.senla.model.dto.ClientDTO;
import com.senla.model.mapper.api.ClientMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/clients")
@Log4j2
public class ClientController {

    private final IClientService clientService;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientController(IClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping(value = "/{{id}}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable String id) throws ServiceException {
        ClientDTO clientDTO = clientService.getEntity(UUID.fromString(id));

        return ResponseEntity.ok().build();
    }
}
