package com.samuel.contratos.repository;

import com.samuel.contratos.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ContratoRepository extends JpaRepository<Contrato, UUID> {
    List<Contrato> findByClienteId(UUID clienteId);

    @Query("SELECT COUNT(c) FROM Contrato c")
    Long countTotalContratos();

    @Query("SELECT TO_CHAR(c.data, 'YYYY-MM') AS mes, COUNT(c) AS total " +
            "FROM Contrato c " +
            "WHERE c.data BETWEEN :startDate AND :endDate " +
            "GROUP BY TO_CHAR(c.data, 'YYYY-MM')")
    List<Object[]> countContratosPorMes(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );


    @Query(value = "SELECT TO_CHAR(c.data, 'YYYY-MM') AS mes, " +
            "COUNT(c) AS quantidade," +
            "SUM(c.prestamista) AS totalPrestamista, " +
            "SUM(c.valorLiquido) AS totalLiquido, " +
            "SUM(c.valorBruto) AS totalBruto " +
            "FROM Contrato c " +
            "WHERE c.data BETWEEN :startDate AND :endDate " +
            "GROUP BY TO_CHAR(c.data, 'YYYY-MM') " +
            "ORDER BY TO_CHAR(c.data, 'YYYY-MM')")
    List<Object[]> calcularTotaisPorMes(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
