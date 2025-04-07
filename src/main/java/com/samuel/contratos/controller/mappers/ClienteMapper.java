package com.samuel.contratos.controller.mappers;

import com.samuel.contratos.controller.dtos.ClienteDto;
import com.samuel.contratos.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteDto dto);

    ClienteDto toDto(Cliente cliente);
}
