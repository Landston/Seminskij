package com.senla.model.mapper.api;

import com.senla.model.Request;
import com.senla.model.dto.RequestDTO;

import java.util.List;

public interface MapperForRequest extends Mapper<Request, RequestDTO> {

    List<RequestDTO> bunchRequestToRequestDTO(List<Request> requests);

    List<Request> bunchDtoRequestToRequest(List<RequestDTO> dtoRequests);

}
