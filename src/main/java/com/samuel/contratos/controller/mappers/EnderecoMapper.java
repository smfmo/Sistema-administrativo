package com.samuel.contratos.controller.mappers;

import com.samuel.contratos.controller.dtos.request.EnderecoRequestDTO;
import com.samuel.contratos.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoRequestDTO dto);

    EnderecoRequestDTO toDto(Endereco endereco);
}
