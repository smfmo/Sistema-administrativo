package com.samuel.contratos.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {

    public static List<String> gerarMesesNoIntervalo(LocalDate startDate, LocalDate endDate) {
        List<String> meses = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        while (!startDate.isAfter(endDate)) {
            meses.add(startDate.format(formatter));
            startDate = startDate.plusMonths(1);
        }

        return meses;
    }
}
