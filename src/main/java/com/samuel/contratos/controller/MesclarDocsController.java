package com.samuel.contratos.controller;

import com.samuel.contratos.service.MesclarDocsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mesclarDocs")
@RequiredArgsConstructor
public class MesclarDocsController {

    private final MesclarDocsService service;

    @GetMapping
    public String mostrarFormMesclagem(){
        return "mesclar-documentos";
    }

    @PostMapping("/download")
    public ResponseEntity<ByteArrayResource> download(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam(value = "filename",
                    required = false,
            defaultValue = "merged") String filename) throws IOException {

        Map<String, Object> mesclarDoc = service.mesclarDocs(files, filename);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + filename + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new ByteArrayResource((byte[]) mesclarDoc.get("content")));
    }
}
