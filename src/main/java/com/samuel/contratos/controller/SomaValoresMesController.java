package com.samuel.contratos.controller;

import com.samuel.contratos.service.SomaValoresMesService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/valores")
@RequiredArgsConstructor
public class SomaValoresMesController {

    private final SomaValoresMesService service;

    @GetMapping
    public String mostrarFormularioPesquisa(Model model) {
        LocalDate dataFim = LocalDate.now();
        LocalDate dataInicio = dataFim.minusMonths(12);

        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        model.addAttribute("valores", Collections.emptyList()); // Lista vazia inicial

        return "calculo-valores";
    }

    @PostMapping
    public String pesquisarValores(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            Model model) {

        List<Map<String, Object>> valores = service.calcularValoresPorMes(dataInicio, dataFim);

        // Calcular totais
        long totalContratos = valores.stream()
                .mapToLong(v -> ((Number) v.get("quantidade")).longValue())
                .sum();

        double totalPrestamista = valores.stream()
                .mapToDouble(v -> (Double) v.get("prestamista"))
                .sum();

        double totalLiquido = valores.stream()
                .mapToDouble(v -> (Double) v.get("liquido"))
                .sum();

        double totalBruto = valores.stream()
                .mapToDouble(v -> (Double) v.get("bruto"))
                .sum();

        model.addAttribute("valores", valores);
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        model.addAttribute("totalContratos", totalContratos);
        model.addAttribute("totalPrestamista", totalPrestamista);
        model.addAttribute("totalLiquido", totalLiquido);
        model.addAttribute("totalBruto", totalBruto);

        return "calculo-valores";
    }
}
