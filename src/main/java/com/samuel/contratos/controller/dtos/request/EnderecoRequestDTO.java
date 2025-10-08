package com.samuel.contratos.controller.dtos.request;


public record EnderecoDto(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf) {
}
