package com.samuel.contratos.service;

import com.samuel.contratos.model.Endereco;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/%s/json/";

    //metodo pra buscar o endereco pelo cep
    public Endereco buscarEnderecoPeloCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(VIA_CEP_URL, cep);
        return restTemplate.getForObject(url, Endereco.class);
    }

}
