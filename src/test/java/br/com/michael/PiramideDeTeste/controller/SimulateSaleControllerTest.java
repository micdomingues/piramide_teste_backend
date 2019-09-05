package br.com.michael.PiramideDeTeste.controller;

import br.com.michael.PiramideDeTeste.gateway.controller.SimulateSale.SimulateSaleController;
import br.com.michael.PiramideDeTeste.gateway.controller.SimulateSale.SimulateSalesResponse;
import br.com.michael.PiramideDeTeste.gateway.controller.handle.GenericExceptionHandler;
import br.com.michael.PiramideDeTeste.usecase.SimulateSale.SimulateSaleOrchestrator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({SimulateSaleController.class, GenericExceptionHandler.class})
public class SimulateSaleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    SimulateSaleOrchestrator simulateSaleOrchestrator;

    @Test
    public void simulate_10_shouldReturnOk() throws Exception {
        // Arrange
        when(simulateSaleOrchestrator.execute(any()))
                .thenReturn(SimulateSalesResponse.builder()
                        .priceForCostumer(BigDecimal.TEN)
                        .priceForHost(BigDecimal.ONE)
                        .build());

        String simulateValue = "10";

        // Act
        final ResultActions result = mvc.perform(MockMvcRequestBuilders
                .get("/sales/simulate")
                .param("amount", simulateValue)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Assert
            result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceForCostumer").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceForCostumer").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceForHost").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceForHost").value(1));
    }

    @Test
    public void simulate_0_shouldThrowConstraintViolationException() throws Exception {

        // Arrange
        String simulateValue = "0";

        // Act
        final ResultActions result = mvc.perform(MockMvcRequestBuilders
                .get("/sales/simulate")
                .param("amount", simulateValue)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Assert
        result.andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("simulate.amount: must be greater than or equal to 0.01"));
    }
}
