package com.samuel.contratos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ArmazenamentoPdfService {

    private final Path rootLocation = Paths.get("uploads").toAbsolutePath().normalize();

    public String armazenarPdf(MultipartFile arquivo) throws IOException {
        if (arquivo.isEmpty()) {
            throw new IOException("Arquivo vazio, não há pdf!");
        }

        if (!Objects.requireNonNull(arquivo.getContentType()).equalsIgnoreCase("application/pdf")) {
            throw new IOException("O arquivo deve ser do tipo PDF");
        }

        String nomeOriginal = arquivo.getOriginalFilename();
        if (nomeOriginal == null || nomeOriginal.contains("..")) {
            throw new IOException("Nome do arquivo inválido: " + nomeOriginal);
        }

        String nomePdf = UUID.randomUUID() + "_" + nomeOriginal;
        Path destino = rootLocation.resolve(nomePdf);

        if (!Files.exists(rootLocation)) {
            Files.createDirectories(rootLocation);
        }

        Files.copy(arquivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        return nomePdf;
    }

    public List<String> armazenarPdf(MultipartFile[] arquivos) throws IOException {
        List<String> nomesPdf = new ArrayList<>();
        for (MultipartFile arquivo : arquivos) {
            nomesPdf.add(armazenarPdf(arquivo));
        }
        return nomesPdf;
    }
}
