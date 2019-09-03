package br.com.michael.PiramideDeTeste.usecases;

import br.com.michael.PiramideDeTeste.usecase.SimulateSale.SimulateSaleHostPayingTaxes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimulateSaleHostPayingTaxesTest {

    @InjectMocks
    SimulateSaleHostPayingTaxes simulateSaleHostPayingTaxes;

    @Test
    public void shouldSuccessfullyCalc() {

        BigDecimal response = simulateSaleHostPayingTaxes.execute(BigDecimal.valueOf(100), BigDecimal.valueOf(2));
        Assert.assertEquals(BigDecimal.valueOf(200), response);

    }

}
