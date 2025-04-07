package com.samuel.contratos.controller.mappers;

import com.samuel.contratos.controller.dtos.EnderecoDto;
import com.samuel.contratos.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoDto dto);

    EnderecoDto toDto(Endereco endereco);
}
