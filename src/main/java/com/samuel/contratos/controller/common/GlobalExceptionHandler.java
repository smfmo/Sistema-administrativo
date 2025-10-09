package com.samuel.contratos.controller.common;

import com.samuel.contratos.exception.ArquivoProcessingException;
import com.samuel.contratos.exception.ContratoException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException e,
                                            RedirectAttributes redirectAttributes) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> {
                   String fieldName = error.getField();
                   String errorMessage = error.getDefaultMessage();
                   errors.put(fieldName, errorMessage);
                });
        redirectAttributes.addFlashAttribute("validationsErrors", errors);
        redirectAttributes.addFlashAttribute("error",
                "Por favor, corrija os erros no formulário");
        return "formulario-cliente";
    }

    @ExceptionHandler(ArquivoProcessingException.class)
    public String handleIOException(ArquivoProcessingException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error",
                "Erro ao salvar arquivos: " + e.getMessage());
        return "redirect:/inicio";
    }

    @ExceptionHandler(ContratoException.class)
    public String handleContratoException(ContratoException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error",
                "Erro ao salvar contrato:" + e.getMessage());
        return "redirect:/inicio";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception e, Model model) {
        model.addAttribute("Error", "Erro interno do Servidor");
        model.addAttribute("message", "Ocorreu um erro inesperado");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        System.err.println("Erro não tratado: " + e.getMessage());

        return "error/500";
    }

}
