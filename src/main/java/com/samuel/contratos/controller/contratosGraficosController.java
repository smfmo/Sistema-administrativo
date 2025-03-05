package com.samuel.contratos.controller;

import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.repository.ContratosRepository;
import com.samuel.contratos.service.ContratoService;
import com.samuel.contratos.service.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class contratosGraficosController {

    private final ContratosRepository contratosRepository;
    private final ContratoService contratoService;

    @Autowired
    public contratosGraficosController(ContratosRepository contratosRepository,
                                       ContratoService contratoService) {
        this.contratosRepository = contratosRepository;
        this.contratoService = contratoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        LocalDate startDate = LocalDate.now().minusMonths(7); // Últimos 6 meses
        LocalDate endDate = LocalDate.now();

        // Gerar todos os meses no intervalo
        List<String> todosMeses = DateUtils.gerarMesesNoIntervalo(startDate, endDate);

        // Buscar contratos por mês no banco de dados
        List<Object[]> contratosPorMes = contratosRepository.countContratosPorMes(startDate, endDate);

        // Criar um mapa para facilitar a busca de contratos por mês
        Map<String, Long> contratosMap = new HashMap<>();
        for (Object[] contrato : contratosPorMes) {
            String mes = (String) contrato[0];
            Long total = (Long) contrato[1];
            contratosMap.put(mes, total);
        }

        // Preencher os totais de contratos para todos os meses
        Long[] totais = new Long[todosMeses.size()];
        for (int i = 0; i < todosMeses.size(); i++) {
            String mes = todosMeses.get(i);
            totais[i] = contratosMap.getOrDefault(mes, 0L); // Preenche com zero se não houver contratos
        }

        model.addAttribute("meses", todosMeses);
        model.addAttribute("totais", totais);

        return "index";
    }

    @GetMapping("controle-contratos")
    public String ExibirTodosContratos(Model model) {
        List<Contrato> contratos = contratoService.buscarTodosOsContratos();

        model.addAttribute("contratos",
                contratoService.buscarTodosOsContratos());

        return "controle-contratos";
    }

}
