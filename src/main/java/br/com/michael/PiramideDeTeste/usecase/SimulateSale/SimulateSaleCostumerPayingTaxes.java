package br.com.michael.PiramideDeTeste.usecase.SimulateSale;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service
public class SimulateSaleCostumerPayingTaxes {

    public BigDecimal execute(BigDecimal amount, BigDecimal tax) {
        Assert.notNull(tax, () -> "Tax must be not null");
        return amount.multiply(tax.multiply(BigDecimal.valueOf(2)));
    }

}
