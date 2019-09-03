package br.com.michael.PiramideDeTeste.usecase.SimulateSale;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service
public class SimulateSaleHostPayingTaxes {

    public BigDecimal execute(BigDecimal amount, BigDecimal tax) {
        return amount.multiply(tax);
    }

}
