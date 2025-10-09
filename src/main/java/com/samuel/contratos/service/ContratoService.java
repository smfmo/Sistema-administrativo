package com.samuel.contratos.service;

import com.samuel.contratos.exception.ArquivoProcessingException;
import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.repository.ContratoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ContratoService {

    private final ContratoRepository repository;
    private final ArmazenamentoPdfService pdfService;

    public ContratoService(ContratoRepository repository,
                           ArmazenamentoPdfService pdfService) {
        this.repository = repository;
        this.pdfService = pdfService;
    }

    @Transactional
    public void save(Contrato contrato, List<MultipartFile> pdf) {
        try {
            List<String> namesPdf = pdfService.receivePdf(pdf);
            contrato.setUrlPdf(namesPdf);
            repository.save(contrato);
        }
        catch (IOException e) {
            throw new ArquivoProcessingException("Erro ao salvar arquivos: " + e.getMessage());
        }
    }

    public List<Contrato> listarContratosPorCliente(UUID id) {
        return repository.findByClienteId(id);
    }

    public Contrato informacoesContrato(UUID id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Contrato não encontrado"));
    }

    public Long totalContratos() {
        return repository.countTotalContratos();
    }
}
