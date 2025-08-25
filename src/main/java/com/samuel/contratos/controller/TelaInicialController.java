package com.samuel.contratos.controller;

import com.samuel.contratos.model.UserAdm;
import com.samuel.contratos.service.ClienteService;
import com.samuel.contratos.service.ContratoService;
import com.samuel.contratos.service.GraficStatisticsService;
import com.samuel.contratos.service.GraficsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
@RequiredArgsConstructor
public class TelaInicialController {

    private final ClienteService clienteService;
    private final GraficsService graficsService;
    private final ContratoService contratoService;
    private final GraficStatisticsService graficsStatisticsService;

    @GetMapping
    public String index(Model model,
                        @AuthenticationPrincipal UserAdm user) {
        model.addAttribute("user", user); //busca o usuário logado.

        Long totalContratos = contratoService.totalContratos();
        Long totalClientes = clienteService.contagemDeClientes();

        model.addAttribute("totalContratos", totalContratos);
        model.addAttribute("totalClientes", totalClientes);

        model.addAttribute("totais", graficsService.mostrarGrafico());
        model.addAttribute("meses", graficsStatisticsService.gerarMesesNoIntervalo());


        return "inicio";
    }

/*    @GetMapping("controle-contratos")
//    public String ExibirTodosContratos(Model model) {
//        model.addAttribute("contratos",
//                contratoService.buscarTodosOsContratos());
//
//        return "controle-contratos";
 }*/
}
