package br.com.michael.PiramideDeTeste.usecase.SimulateSale;

import br.com.michael.PiramideDeTeste.gateway.controller.SimulateSale.SimulateSalesResponse;
import br.com.michael.PiramideDeTeste.gateway.repository.RetrieveSalesTaxes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class SimulateSaleOrchestrator {

    private final SimulateSaleCostumerPayingTaxes costumerPayingTaxes;
    private final SimulateSaleHostPayingTaxes hostPayingTaxes;
    private final RetrieveSalesTaxes retrieveSalesTaxes;

    public SimulateSalesResponse execute(BigDecimal amount) {

        BigDecimal tax = retrieveSalesTaxes.execute();

        Assert.notNull(tax, () -> "Tax must be not null");
        Assert.isTrue(tax.compareTo(BigDecimal.ZERO) > 0, "Tax must be greater than zero");

        return SimulateSalesResponse.builder()
                .priceForCostumer(costumerPayingTaxes.execute(amount, tax))
                .priceForHost(hostPayingTaxes.execute(amount, tax))
                .build();
    }
}
