package com.samuel.contratos.service;

import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.repository.ContratoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ContratoService {

    private final ContratoRepository repository;

    public ContratoService(ContratoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(Contrato contrato) {
        repository.save(contrato);
    }

    public List<Contrato> listarContratosPorCliente(UUID id) {
        return repository.findByClienteId(id);
    }

    public Contrato informacoesContrato(UUID id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Contrato não encontrado"));
    }

    public Long totalContratos() {
        return repository.countTotalContratos();
    }
}
