package com.samuel.contratos.controller.mappers;

import com.samuel.contratos.controller.dtos.request.ContratoRequestDTO;
import com.samuel.contratos.model.Contrato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContratoMapper {

    Contrato toEntity(ContratoRequestDTO dto);

    ContratoRequestDTO toDto(Contrato contrato);
}
