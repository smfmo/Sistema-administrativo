package com.samuel.contratos.service;

import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.repository.ContratosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GraficosService {

    private final ContratosRepository contratosRepository;
    private final ContratoService contratoService;

    public Long[] mostrarGrafico(){

        List<String> todosMeses = gerarMesesNoIntervalo();

        List<Object[]> contratosPorMes = contratosPorMes();

        Map<String, Long> contratosMap = new HashMap<>();
        for (Object[] contrato : contratosPorMes) {
            String mes = (String) contrato[0];
            Long total = (Long) contrato[1];
            contratosMap.put(mes, total);
        }

        Long[] totais = new Long[todosMeses.size()];
        for (int i = 0; i < todosMeses.size(); i++) {
            String mes = todosMeses.get(i);
            totais[i] = contratosMap.getOrDefault(mes, 0L); // Preenche com zero se não houver contratos
        }

        return totais;
    }

    public Long[] mostrarGraficosDoCliente(UUID clienteId){
        List<String> todosMeses = gerarMesesNoIntervalo();
        List<Contrato> contratosDoCliente = contratoService.listarContratosPorCliente(clienteId);

        Map<String, Long> contratosMap = new HashMap<>();
        for (Contrato contrato : contratosDoCliente) {
            String mes = contrato.getData().format(DateTimeFormatter.ofPattern("yyyy-MM"));
            contratosMap.put(mes, contratosMap.getOrDefault(mes, 0L) + 1);
        }

        Long[] totais = new Long[todosMeses.size()];
        for (int i = 0; i < todosMeses.size(); i++) {
            String mes = todosMeses.get(i);
            totais[i] = contratosMap.getOrDefault(mes, 0L);
        }

        return totais;
    }

    public Long totalContratos(){
        return contratosRepository.countTotalContratos();
    }

    public List<Object[]> contratosPorMes(){
        LocalDate startDate = LocalDate.now().minusMonths(12);
        LocalDate endDate = LocalDate.now();
        return contratosRepository.countContratosPorMes(startDate, endDate);
    }

    public List<String> gerarMesesNoIntervalo(){
        LocalDate startDate = LocalDate.now().minusMonths(12);
        LocalDate endDate = LocalDate.now();

        List<String> meses = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        while (!startDate.isAfter(endDate)) {
            meses.add(startDate.format(formatter));
            startDate = startDate.plusMonths(1);
        }
        return meses;
    }
}
