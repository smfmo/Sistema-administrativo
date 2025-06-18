package com.samuel.contratos.controller;

import com.samuel.contratos.model.UserAdm;
import com.samuel.contratos.service.ClienteService;
import com.samuel.contratos.service.GraficosService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
@RequiredArgsConstructor
public class ContratosGraficosController {

    private final ClienteService clienteService;
    private final GraficosService graficosService;

    @GetMapping
    public String index(Model model,
                        @AuthenticationPrincipal UserAdm user) {
        model.addAttribute("user", user); //busca o usuário logado.

        Long totalContratos = graficosService.totalContratos();
        Long totalClientes = clienteService.contagemDeClientes();

        model.addAttribute("totalContratos", totalContratos);
        model.addAttribute("totalClientes", totalClientes);

        model.addAttribute("totais", graficosService.mostrarGrafico());
        model.addAttribute("meses", graficosService.gerarMesesNoIntervalo());


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
