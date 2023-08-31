package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReajusteServiceTest {
    private Funcionario funcionario;
    private ReajusteService service;

    @BeforeAll
    void inicializarAntesDeTodos() {
        service = new ReajusteService();
    }

    @BeforeEach
    void inicializar() {
        funcionario = new Funcionario(
                "Ana",
                LocalDate.now(),
                new BigDecimal("1000.00"));
    }

    @Test
    void reajusteDeveriaSerDeTresPorcentoQuandoDesempenhoForADesejar() {
        service.concederReajuste(funcionario, Desempenho.A_DESEJAR);
        assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
    }

    @Test
    void reajusteDeveriaSerDeQuinzePorcentoQuandoDesempenhoForBom() {
        service.concederReajuste(funcionario, Desempenho.BOM);
        assertEquals(new BigDecimal("1150.00"), funcionario.getSalario());
    }

    @Test
    void reajusteDeveriaSerDeVintePorcentoQuandoDesempenhoForOtimo() {
        service.concederReajuste(funcionario, Desempenho.OTIMO);
        assertEquals(new BigDecimal("1200.00"), funcionario.getSalario());
    }

    @Test
    void reajusteDeveriaSerDeQuarentaPorcentoQuandoDesempenhoForEspetacular() {
        service.concederReajuste(funcionario, Desempenho.ESPETACULAR);
        assertEquals(new BigDecimal("1400.00"), funcionario.getSalario());
    }
}


