package com.samuel.contratos.service;

import com.samuel.contratos.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SomaValoresMesService {

    private final ContratoRepository repository;

    public List<Map<String, Object>> calcularValoresPorMes(LocalDate dataInicio,
                                                           LocalDate dataFim){
        List<Object[]> resultados = repository.calcularTotaisPorMes(dataInicio, dataFim);
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

    public void carregarDados(LocalDate dataInicio,
                              LocalDate dataFim,
                              Model model) {

        List<Map<String, Object>> valores  = calcularValoresPorMes(dataInicio, dataFim);

        long totalContratos = valores.stream()
                .mapToLong(v -> ((Number) v.get("quantidade"))
                        .longValue()).sum();

        double totalPrestamista = valores.stream()
                .mapToDouble(v -> ((Number) v.get("prestamista"))
                        .doubleValue()).sum();

        double totalLiquido = valores.stream()
                .mapToDouble(v -> ((Number) v.get("liquido"))
                        .doubleValue()).sum();

        double totalBruto = valores.stream()
                .mapToDouble(v -> ((Number) v.get("bruto"))
                        .doubleValue()).sum();

        model.addAttribute("valores", valores);
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        model.addAttribute("totalContratos", totalContratos);
        model.addAttribute("totalPrestamista", totalPrestamista);
        model.addAttribute("totalLiquido", totalLiquido);
        model.addAttribute("totalBruto", totalBruto);
    }
}
