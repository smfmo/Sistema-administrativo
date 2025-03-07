package com.samuel.contratos.controller;


import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.model.TiposDeContrato;
import com.samuel.contratos.repository.ContratosRepository;
import com.samuel.contratos.service.ClienteService;
import com.samuel.contratos.service.ContratoService;
import com.samuel.contratos.service.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/contratos")
public class ContratosController {

    private final ContratoService contratosService;
    private final ClienteService clienteService;

    @Autowired
    public ContratosController(ContratoService contratosService,
                               ClienteService clienteService) {
        this.clienteService = clienteService;
        this.contratosService = contratosService;
    }

    @GetMapping("/form/addContrato")
    public String mostrarFormularioContrato(Model model) {
        model.addAttribute("contrato", new Contrato());
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("tiposDeContrato", TiposDeContrato.values());
        return "formulario-contrato";
    }
    @PostMapping
    public String salvarContrato(@ModelAttribute Contrato contrato) {
        contratosService.salvarContrato(contrato);
        return "redirect:/inicio";
    }

    @GetMapping("/cliente/{id}")
    public String contratosPorCliente(@PathVariable UUID id,
                                      Model model) {

        LocalDate startDate = LocalDate.now().minusMonths(7); // Últimos 6 meses
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
        model.addAttribute("clienteId", id);
        model.addAttribute("contratos", contratosDoCliente);

        return "controle-contratos";
    }
}
