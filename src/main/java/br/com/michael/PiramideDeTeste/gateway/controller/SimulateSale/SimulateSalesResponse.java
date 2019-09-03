package br.com.michael.PiramideDeTeste.gateway.controller.SimulateSale;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SimulateSalesResponse {
    BigDecimal priceForCostumer;
    BigDecimal priceForHost;
}
