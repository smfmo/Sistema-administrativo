package com.samuel.contratos.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MesclarDocsService {

    public Map<String, Object> mesclarDocs(List<MultipartFile> files, String customName) throws IOException {

        if (files == null || files.isEmpty()){
            throw new IllegalArgumentException("Nenhum arquivo foi encontrado");
        }

        PDFMergerUtility mesclar = new PDFMergerUtility();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PDDocument finalPdf = new PDDocument()){
            for (MultipartFile file : files){
                if (!Objects.equals(file.getContentType(), "application/pdf")){
                    throw new IllegalArgumentException("Apenas arquivos pdf são permitidos");
                }
                try(PDDocument doc = Loader.loadPDF(file.getBytes())){
                    mesclar.appendDocument(finalPdf, doc);
                }
            }
            finalPdf.save(outputStream);
        }
        Map<String, Object> mesclarDocs = new HashMap<>();
        mesclarDocs.put("content", outputStream.toByteArray());
        mesclarDocs.put("filename", (customName != null  ? customName : "merged") + ".pdf");
        return mesclarDocs;
    }
}
