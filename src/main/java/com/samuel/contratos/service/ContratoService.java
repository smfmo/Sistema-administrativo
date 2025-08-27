package com.samuel.contratos.service;

import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ContratoService {

    private final ContratoRepository contratoRepository;

    public void salvarContrato(Contrato contrato) {
        contratoRepository.save(contrato);
    }

    public List<Contrato> listarContratosPorCliente(UUID id) {
        return contratoRepository.findByClienteId(id);
    }

    public Contrato informacoesContrato(UUID id) {
        return contratoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Contrato não encontrado"));
    }

    public Long totalContratos() {
        return contratoRepository.countTotalContratos();
    }
}
