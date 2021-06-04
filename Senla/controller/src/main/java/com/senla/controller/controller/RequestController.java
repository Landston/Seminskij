package com.senla.controller.controller;

import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IRequestService;
import com.senla.model.dto.RequestDTO;
import com.senla.model.mapper.api.MapperForRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/request")
@Log4j2
public class RequestController {

    private final IRequestService requestService;

    private final MapperForRequest requestMapper;


    public RequestController(IRequestService requestService, MapperForRequest requestMapper) {
        this.requestService = requestService;
        this.requestMapper = requestMapper;
    }


    @GetMapping(value = "/{{id}}")
    public ResponseEntity<RequestDTO> getRequest(@PathVariable String id) throws ServiceException {
        RequestDTO dto = requestService.getRequestByBook(UUID.fromString(id));

        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/close")
    public ResponseEntity<RequestDTO> closeRequest(@RequestParam String id) throws ServiceException, DAOException {
        RequestDTO dto = requestService.closeRequestById(UUID.fromString(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<RequestDTO> createRequest(@RequestParam String id) throws ServiceException {
        RequestDTO dto = requestService.createRequest(UUID.fromString(id));

        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<RequestDTO>> getAllRequests() throws ServiceException {

        return ResponseEntity.ok(requestService.getAllRequests());
    }

}
