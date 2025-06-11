package com.samuel.contratos.service;

import com.samuel.contratos.controller.mappers.ContratoMapper;
import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.repository.ContratosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ContratoService {

    private final ContratosRepository contratosRepository;
    private final ContratoMapper contratoMapper;

    public void salvarContrato(Contrato contrato) {
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

    public List<Map<String, Object>> calcularValoresPorMes(LocalDate dataInicio,
                                                           LocalDate dataFim){

        List<Object[]> resultados = contratosRepository.calcularTotaisPorMes(dataInicio, dataFim);
        List<Map<String, Object>> valoresPorMes = new ArrayList<>();

        for (Object[] resultado : resultados){
            Map<String, Object> valores = new LinkedHashMap<>();
            valores.put("mes", resultado[0]);
            valores.put("quantidade", resultado[1]);
            valores.put("prestamista", resultado[2]);
            valores.put("liquido", resultado[3]);
            valores.put("bruto", resultado[4]);
            valoresPorMes.add(valores);
        }

        return valoresPorMes;
    }
}
