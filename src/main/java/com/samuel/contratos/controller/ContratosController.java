package com.samuel.contratos.controller;

import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.model.TiposDeContrato;
import com.samuel.contratos.service.ArmazenamentoPdfService;
import com.samuel.contratos.service.ClienteService;
import com.samuel.contratos.service.ContratoService;
import com.samuel.contratos.service.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/contratos")
@RequiredArgsConstructor
public class ContratosController {

    private final ContratoService contratosService;
    private final ClienteService clienteService;
    private final ArmazenamentoPdfService armazenamentoPdfService;

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

        LocalDate startDate = LocalDate.now().minusMonths(12); // Contratos dos últimos 12 meses
        LocalDate endDate = LocalDate.now();

        // Gerar todos os meses no intervalo
        List<String> todosMeses = DateUtils.gerarMesesNoIntervalo(startDate, endDate);

        // Buscar contratos por mês no banco de dados para o cliente específico
        List<Contrato> contratosDoCliente = contratosService.listarContratosPorCliente(id);

        // Criar um mapa para facilitar a busca de contratos por mês
        Map<String, Long> contratosMap = new HashMap<>();
        for (Contrato contrato : contratosDoCliente) {
            String mes = contrato.getData().format(DateTimeFormatter.ofPattern("yyyy-MM"));
            contratosMap.put(mes, contratosMap.getOrDefault(mes, 0L) + 1);
        }

        // Preencher os totais de contratos para todos os meses
        Long[] totais = new Long[todosMeses.size()];
        for (int i = 0; i < todosMeses.size(); i++) {
            String mes = todosMeses.get(i);
            totais[i] = contratosMap.getOrDefault(mes, 0L); // Preenche com zero se não houver contratos
        }

        model.addAttribute("meses", todosMeses);
        model.addAttribute("totais", totais);
        model.addAttribute("contratos", contratosDoCliente);

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
