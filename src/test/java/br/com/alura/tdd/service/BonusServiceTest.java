package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BonusServiceTest {
    private Funcionario funcionarioComSalarioMuitoAlto;
    private Funcionario funcionario;
    private Funcionario funcionarioComSalarioDeExatamente10000;
    private BonusService service;

    @BeforeAll
    void inicializar() {
        service = new BonusService();
        funcionarioComSalarioMuitoAlto = new Funcionario(
                "Marcos Paulo Paixao",
                LocalDate.now(),
                new BigDecimal("25000"));
        funcionario = new Funcionario(
                "Marcos Paulo Paixao",
                LocalDate.now(),
                new BigDecimal("2500"));
        funcionarioComSalarioDeExatamente10000 = new Funcionario(
                "Marcos Paulo Paixao",
                LocalDate.now(),
                new BigDecimal("10000"));
    }

    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {
        assertThrows(IllegalArgumentException.class,
                () -> this.service.calcularBonus(funcionarioComSalarioMuitoAlto));
    }

    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAltoTryCatch() {
        try {
            this.service.calcularBonus(funcionarioComSalarioMuitoAlto);
            fail("Nao lancou excecao");
        } catch (Exception e) {
            assertEquals("Funcionario com salario maior que R$ 10.000,00 nao pode receber bonus.",
                    e.getMessage());
        }
    }

    @Test
    void bonusDeveriaSer10PorCentoDoSalario() {
        BigDecimal bonus = this.service.calcularBonus(funcionario);
        assertEquals(new BigDecimal("250.00"), bonus);
    }

    @Test
    void bonusDeveriaSer10PorCentoDoSalarioParaSalarioDeExatamente10000() {
        BigDecimal bonus = this.service.calcularBonus(funcionarioComSalarioDeExatamente10000);
        assertEquals(new BigDecimal("1000.00"), bonus);
    }
}