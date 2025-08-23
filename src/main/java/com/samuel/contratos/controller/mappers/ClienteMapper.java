package com.samuel.contratos.controller.mappers;

import com.samuel.contratos.controller.dtos.ClienteDto;
import com.samuel.contratos.model.Cliente;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePartial(
            ClienteDto clienteDto,
            @MappingTarget Cliente cliente
    );

    Cliente toEntity(ClienteDto dto);

    ClienteDto toDto(Cliente cliente);
}
