package br.com.michael.PiramideDeTeste.gateway.controller.SimulateSale;

import br.com.michael.PiramideDeTeste.usecase.SimulateSale.SimulateSaleOrchestrator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Controller
@AllArgsConstructor
@Validated
public class SimulateSaleController {

    private final SimulateSaleOrchestrator orchestrator;

    @GetMapping(value = "/sales/simulate")
    public ResponseEntity<SimulateSalesResponse> simulate(@Valid @RequestParam @DecimalMin("0.01") final BigDecimal amount) {
        return ResponseEntity.status(HttpStatus.OK).body(orchestrator.execute(amount));
    }

}
