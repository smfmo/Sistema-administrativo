package com.samuel.contratos.service;

import com.samuel.contratos.model.Contrato;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GraficsService {

    private final ContratoService contratoService;
    private final GraficStatisticsService graficsStatisticsService;

    public Long[] mostrarGrafico(){
        List<Object[]> contratosPorMes = graficsStatisticsService.contratosPorMes();
        Map<String, Long> contratosMap = new HashMap<>();

        for (Object[] contrato : contratosPorMes) {
            String mes = (String) contrato[0];
            Long total = (Long) contrato[1];
            contratosMap.put(mes, total);
        }
        return totaisPorMes(contratosMap);
    }

    public Long[] mostrarGraficosDoCliente(UUID clienteId){
        List<Contrato> contratosDoCliente = contratoService.listarContratosPorCliente(clienteId);
        Map<String, Long> contratosMap = new HashMap<>();

        for (Contrato contrato : contratosDoCliente) {
            String mes = contrato.getData().format(DateTimeFormatter.ofPattern("yyyy-MM"));
            contratosMap.put(mes, contratosMap.getOrDefault(mes, 0L) + 1);
        }
        return totaisPorMes(contratosMap);
    }

    private Long[] totaisPorMes(Map<String, Long> contratosMap) {
        List<String> todosMeses = graficsStatisticsService.gerarMesesNoIntervalo();
        Long[] totais = new Long[todosMeses.size()];

        for (int i = 0; i < todosMeses.size(); i++) {
            String meses = todosMeses.get(i);
            totais[i] = contratosMap.getOrDefault(meses, 0L);
        }
        return totais;
    }
}
