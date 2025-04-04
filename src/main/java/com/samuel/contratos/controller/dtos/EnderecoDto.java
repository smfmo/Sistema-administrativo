package com.samuel.contratos.controller.dtos;

import com.samuel.contratos.model.Endereco;

public record EnderecoDto(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf) {

    public Endereco mapearParaEndereco() {
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);
        endereco.setLocalidade(localidade);
        endereco.setUf(uf);

        return endereco;
    }
}
