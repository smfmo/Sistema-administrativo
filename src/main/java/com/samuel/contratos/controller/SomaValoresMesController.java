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

@Controller
@RequestMapping("/valores")
@RequiredArgsConstructor
public class SomaValoresMesController {

    private final SomaValoresMesService service;

    @GetMapping
    public String mostrarFormularioPesquisa(Model model) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(12);

        model.addAttribute("dataInicio", startDate);
        model.addAttribute("dataFim", endDate);
        model.addAttribute("valores", Collections.emptyList());

        return "calculo-valores";
    }

    @PostMapping
    public String pesquisarValores(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            Model model) {
        service.carregarDados(dataInicio, dataFim, model);
        return "calculo-valores";
    }
}
