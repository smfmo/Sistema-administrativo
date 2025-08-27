package com.samuel.contratos.service;

import com.samuel.contratos.repository.ContratoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class GraficStatisticsService {

    private final ContratoRepository contratoRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
    private static final int DEFAULT_MONTHS_BACK = 12;

    public GraficStatisticsService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public List<Object[]> contratosPorMes() {
        LocalDate startDate = LocalDate.now().minusMonths(DEFAULT_MONTHS_BACK);
        LocalDate endDate = LocalDate.now();
        return contratoRepository.countContratosPorMes(startDate, endDate);
    }

    public List<String> gerarMesesNoIntervalo() {
        LocalDate startDate = LocalDate.now().minusMonths(DEFAULT_MONTHS_BACK);
        LocalDate endDate = LocalDate.now();
        List<String> meses = new ArrayList<>();

        while (!startDate.isAfter(endDate)) {
            meses.add(startDate.format(FORMATTER));
            startDate = startDate.plusMonths(1);
        }
        return meses;
    }
}
