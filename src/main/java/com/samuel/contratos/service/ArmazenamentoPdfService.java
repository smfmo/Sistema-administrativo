package com.samuel.contratos.service;

import com.samuel.contratos.util.PdfStorageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArmazenamentoPdfService {
    public List<String> receivePdf(MultipartFile[] files) throws IOException {
        List<String> nomesPdf = new ArrayList<>();
        for (MultipartFile arquivo : files) {
            nomesPdf.add(PdfStorageUtil.processPdf(arquivo));
        }
        return nomesPdf;
    }
}
