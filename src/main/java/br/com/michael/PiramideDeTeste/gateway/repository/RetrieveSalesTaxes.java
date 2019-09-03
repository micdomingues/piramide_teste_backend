package br.com.michael.PiramideDeTeste.gateway.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class RetrieveSalesTaxes {

    public BigDecimal execute() {
        return BigDecimal.valueOf(2);
    }
}
