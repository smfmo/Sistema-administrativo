package com.samuel.contratos.controller;

import com.samuel.contratos.controller.dtos.request.ContratoRequestDTO;
import com.samuel.contratos.controller.mappers.ContratoMapper;
import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.model.Enum.TiposDeContrato;
import com.samuel.contratos.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/contratos")
@RequiredArgsConstructor
public class ContratosController {

    private final ContratoService contratosService;
    private final ClienteService clienteService;
    private final GraficsService graficsService;
    private final GraficStatisticsService graficsStatisticsService;
    private final ContratoMapper mapper;

    @GetMapping
    public String getContractsForm(@ModelAttribute ContratoRequestDTO request,
                                   Model model) {
        model.addAttribute("contrato", mapper.toEntity(request));
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("tiposDeContrato", TiposDeContrato.values());
        return "formulario-contrato";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid ContratoRequestDTO request,
                       @RequestParam("pdf") MultipartFile[] pdf,
                       RedirectAttributes redirectAttributes,
                       BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "formulario-contrato";
            }

            Contrato contrato = mapper.toEntity(request);
            contratosService.save(contrato, pdf);
            redirectAttributes.addFlashAttribute("success", "Contrato salvo com sucesso!");
        }
        catch (IOException e) {
            redirectAttributes.addFlashAttribute("error",
                    "Erro ao salvar arquivos: " + e.getMessage());
            return "redirect:/inicio";
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Erro ao salvar contrato: " + e.getMessage());
            return "redirect:/inicio";
        }
        return "redirect:/inicio";
    }

    @GetMapping("/cliente/{id}")
    public String contratosPorCliente(@PathVariable UUID id,
                                      Model model) {
        model.addAttribute("meses", graficsStatisticsService.gerarMesesNoIntervalo());
        model.addAttribute("totais", graficsService.mostrarGraficosDoCliente(id));
        model.addAttribute("contratos", contratosService.listarContratosPorCliente(id));
        return "controle-contratos";
    }

    @GetMapping("/{id}")
    public String informacoesContrato(@PathVariable("id") UUID id,
                                      Model model) {
        Contrato contrato = contratosService.informacoesContrato(id);
        model.addAttribute("contrato", contrato);
        return "informacoes-contratos";
    }
}
