package com.samuel.contratos.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

public class PdfStorageUtil {

    private static final Path ROOT_LOCATION = Paths.get("uploads").toAbsolutePath().normalize();

    public static String processPdf(MultipartFile file) throws IOException{
        validations(file);

        String originalFileName = file.getOriginalFilename();

        String pdfName = UUID.randomUUID() + "_" + originalFileName;
        Path destination = ROOT_LOCATION.resolve(pdfName);

        Files.copy(
                file.getInputStream(),
                destination,
                StandardCopyOption.REPLACE_EXISTING
        );

        return pdfName;
    }

    public static void validations(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Arquivo vazio, não há pdf.");
        }

        if (!Objects.requireNonNull(file.getContentType()).equalsIgnoreCase("application/pdf")) {
            throw new IOException("O arquivo deve ser do tipo pdf.");
        }

        String originalName = file.getOriginalFilename();
        if (originalName == null || originalName.contains("..")) {
            throw new IOException("Nome do arquivo inválido: " + originalName);
        }

        if (!Files.exists(ROOT_LOCATION)) {
            Files.createDirectories(ROOT_LOCATION);
        }
    }
}
