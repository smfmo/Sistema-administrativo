package com.samuel.contratos.service;

import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.repository.ContratosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoService {

    private final ContratosRepository contratosRepository;

    @Autowired
    public ContratoService(ContratosRepository contratosRepository) {
        this.contratosRepository = contratosRepository;
    }

    public Contrato salvarContrato(Contrato contrato) {
        return contratosRepository.save(contrato);
    }

    public List<Contrato> buscarTodosOsContratos() {
        return contratosRepository.findAll();
    }

}
