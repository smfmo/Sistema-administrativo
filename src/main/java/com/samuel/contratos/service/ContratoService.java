package com.samuel.contratos.service;

import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.repository.ContratosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ContratoService {

    private final ContratosRepository contratosRepository;

    @Autowired
    public ContratoService(ContratosRepository contratosRepository) {
        this.contratosRepository = contratosRepository;
    }

    public void salvarContrato(Contrato contrato) {
        contratosRepository.save(contrato);
    }

    public List<Contrato> buscarTodosOsContratos() {
        return contratosRepository.findAll();
    }


    public List<Contrato> listarContratosPorCliente(UUID id) {
        return contratosRepository.findByClienteId(id);
    }

    public Contrato informacoesContrato(UUID id) {
        return contratosRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Contrato não encontrado"));
    }
}
