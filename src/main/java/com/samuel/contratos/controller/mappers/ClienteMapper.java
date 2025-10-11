package com.samuel.contratos.controller.mappers;

import com.samuel.contratos.controller.dtos.request.ClienteRequestDTO;
import com.samuel.contratos.model.Cliente;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePartial(
            ClienteRequestDTO clienteRequestDTO,
            @MappingTarget Cliente cliente
    );

    Cliente toEntity(ClienteRequestDTO dto);

    ClienteRequestDTO toDto(Cliente cliente);
}
