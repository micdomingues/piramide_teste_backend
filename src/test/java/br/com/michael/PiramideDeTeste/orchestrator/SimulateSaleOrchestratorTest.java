package br.com.michael.PiramideDeTeste.orchestrator;

import br.com.michael.PiramideDeTeste.gateway.controller.SimulateSale.SimulateSalesResponse;
import br.com.michael.PiramideDeTeste.gateway.repository.RetrieveSalesTaxes;
import br.com.michael.PiramideDeTeste.usecase.SimulateSale.SimulateSaleCostumerPayingTaxes;
import br.com.michael.PiramideDeTeste.usecase.SimulateSale.SimulateSaleHostPayingTaxes;
import br.com.michael.PiramideDeTeste.usecase.SimulateSale.SimulateSaleOrchestrator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SimulateSaleOrchestratorTest {

    @InjectMocks
    SimulateSaleHostPayingTaxes simulateSaleHostPayingTaxes;
    @InjectMocks
    SimulateSaleCostumerPayingTaxes simulateSaleCostumerPayingTaxes;
    @Mock
    RetrieveSalesTaxes retrieveSalesTaxes;

    @InjectMocks
    SimulateSaleOrchestrator simulateSaleOrchestrator;


    @Before
    public void setUp() {
        simulateSaleOrchestrator = new SimulateSaleOrchestrator(simulateSaleCostumerPayingTaxes, simulateSaleHostPayingTaxes, retrieveSalesTaxes);
    }

    @Test
    public void shouldSuccessfullyCalcForCostumerAndHost() {

        when(retrieveSalesTaxes.execute()).thenReturn(BigDecimal.valueOf(2));
        SimulateSalesResponse simulateSalesResponse = simulateSaleOrchestrator.execute(BigDecimal.valueOf(100));
        Assert.assertEquals(BigDecimal.valueOf(200), simulateSalesResponse.getPriceForHost());
        Assert.assertEquals(BigDecimal.valueOf(400), simulateSalesResponse.getPriceForCostumer());

    }

    @Test
    public void shouldThrowErrorWhenTaxIsNullCalc() {
        when(retrieveSalesTaxes.execute()).thenReturn(null);
        try {
            simulateSaleOrchestrator.execute(BigDecimal.valueOf(100));
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Tax must be not null", e.getMessage());
        }

    }

    @Test
    public void shouldThrowErrorWhenTaxIsZeroCalc() {
        when(retrieveSalesTaxes.execute()).thenReturn(BigDecimal.ZERO);
        try {
            simulateSaleOrchestrator.execute(BigDecimal.valueOf(100));
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Tax must be greater than zero", e.getMessage());
        }

    }
}
