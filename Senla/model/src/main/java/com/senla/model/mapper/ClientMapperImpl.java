package com.senla.model.mapper;

import com.senla.model.Client;
import com.senla.model.dto.ClientDTO;
import com.senla.model.mapper.api.ClientMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapperImpl implements ClientMapper {
    @Override
    public ClientDTO toDto(Client entity) {
        return new ClientDTO(
                entity.getId(),
                entity.getName(),
                entity.getMail()
        );
    }

    @Override
    public Client toEntity(ClientDTO dto) {
        return new Client(
                dto.getId(),
                dto.getName(),
                dto.getMail()
        );
    }
}
