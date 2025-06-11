package com.samuel.contratos.service;

import com.samuel.contratos.repository.ContratosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SomaValoresMesService {

    private final ContratosRepository repository;

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
}
