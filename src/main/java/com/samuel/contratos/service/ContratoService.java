package com.samuel.contratos.service;

import com.samuel.contratos.controller.dtos.ContratoDto;
import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.repository.ClientesRepository;
import com.samuel.contratos.repository.ContratosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContratoService {

    private final ContratosRepository contratosRepository;
    private final ClientesRepository clientesRepository;

    public void salvarContrato(ContratoDto contratoDto) {

        Cliente cliente = clientesRepository.findById(contratoDto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado")
        );

        Contrato contrato = contratoDto.mapearParaContrato(cliente);

        contratosRepository.save(contrato);
    }

    /**
     *  public List<Contrato> buscarTodosOsContratos() {
     *         return contratosRepository.findAll();
     *     }
     *
     */

    public List<Contrato> listarContratosPorCliente(UUID id) {
        return contratosRepository.findByClienteId(id);
    }

    public Contrato informacoesContrato(UUID id) {
        return contratosRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Contrato não encontrado"));
    }
}
