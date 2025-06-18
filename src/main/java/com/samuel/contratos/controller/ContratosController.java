package com.samuel.contratos.controller;

import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.model.Enum.TiposDeContrato;
import com.samuel.contratos.service.ArmazenamentoPdfService;
import com.samuel.contratos.service.ClienteService;
import com.samuel.contratos.service.ContratoService;
import com.samuel.contratos.service.GraficosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final ArmazenamentoPdfService armazenamentoPdfService;
    private final GraficosService graficosService;

    @GetMapping("/form/addContrato")
    public String mostrarFormularioContrato(@ModelAttribute Contrato contrato,
                                            Model model) {
        model.addAttribute("contrato", contrato);

        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("tiposDeContrato", TiposDeContrato.values());
        return "formulario-contrato";
    }
    @PostMapping
    public String salvarContrato(@ModelAttribute Contrato contrato,
                                 @RequestParam("pdf") MultipartFile[] pdf,
                                 RedirectAttributes redirectAttributes) {

        try {
            List<String> nomesPdf = armazenamentoPdfService.armazenarPdf(pdf);
            contrato.setUrlPdf(nomesPdf);
            contratosService.salvarContrato(contrato);

            redirectAttributes.addFlashAttribute("success", "Contrato salvo com sucesso!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error",
                    "Erro ao salvar arquivos: " + e.getMessage());
            return "redirect:/inicio";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Erro ao salvar contrato: " + e.getMessage());
            return "redirect:/inicio";
        }
        return "redirect:/inicio";
    }

    @GetMapping("/cliente/{id}")
    public String contratosPorCliente(@PathVariable UUID id,
                                      Model model) {

        model.addAttribute("meses", graficosService.gerarMesesNoIntervalo());
        model.addAttribute("totais", graficosService.mostrarGraficosDoCliente(id));
        model.addAttribute("contratos", contratosService.listarContratosPorCliente(id));

        return "controle-contratos";
    }

    @GetMapping("/{id}")
    public String informacoesContrato(@PathVariable UUID id,
                                      Model model) {
        Contrato contrato = contratosService.informacoesContrato(id);
        model.addAttribute("contrato", contrato);
        return "informacoes-contratos";
    }
}
