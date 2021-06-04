package com.senla.controller.controller;

import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IClientService;
import com.senla.dao.ClientDAO;
import com.senla.model.dto.ClientDTO;
import com.senla.model.mapper.api.ClientMapper;
import com.senla.ui.actions.request.RequestSortAction;
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

        return ResponseEntity.ok(clientDTO);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO dto) throws ServiceException {
        ClientDTO createdClient = clientService.addEntity(dto);

        return ResponseEntity.ok(createdClient);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO dto) throws ServiceException {
        ClientDTO updatedClientDto = clientService.updateEntity(dto);

        return ResponseEntity.ok(updatedClientDto);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ClientDTO> deleteClient(@RequestParam String id) throws ServiceException {
        ClientDTO clientDTO = clientService.deleteEntity(UUID.fromString(id));

        return ResponseEntity.ok(clientDTO);
    }
}
