package com.samuel.contratos.controller.mappers;

import com.samuel.contratos.controller.dtos.ContratoDto;
import com.samuel.contratos.model.Contrato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContratoMapper {

    Contrato toEntity(ContratoDto dto);

    ContratoDto toDto(Contrato contrato);
}
