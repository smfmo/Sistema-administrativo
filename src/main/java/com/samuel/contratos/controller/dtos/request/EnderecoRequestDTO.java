package com.samuel.contratos.controller.dtos.request;


public record EnderecoRequestDTO(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf
) {}
