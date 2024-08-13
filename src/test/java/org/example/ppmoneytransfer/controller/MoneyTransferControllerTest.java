package org.example.ppmoneytransfer.controller;

import org.example.ppmoneytransfer.transfer.controller.MoneyTransferController;
import org.example.ppmoneytransfer.transfer.dto.TransferRequest;
import org.example.ppmoneytransfer.service.MoneyTransferService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.example.ppmoneytransfer.utils.JsonConverter.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MoneyTransferControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MoneyTransferService moneyTransferService;

    @InjectMocks
    private MoneyTransferController moneyTransferController;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(moneyTransferController).build();

    }

    @AfterEach
    public void tearDown() throws Exception {
        if (closeable != null) closeable.close();
    }


    @Test
    @DisplayName("Given valid values should return success")
    public void givenValidValuesShouldReturnSuccess() throws Exception {
        TransferRequest request = new TransferRequest(
                BigDecimal.valueOf(123.45),
                1,
                2
        );

        String expectedJson = """
                { "success": true, "message": "Transferred successfully"}
                """;

        mockMvc.perform(post("/api/v1/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        // @todo: .andExpect(content().json(expectedJson));
    }

    @Test
    @DisplayName("Given missing value should return bad request")
    public void givenMissingValueShouldReturnBadRequest() throws Exception {
        TransferRequest request = new TransferRequest(
                null,
                1,
                2
        );

        mockMvc.perform(post("/api/v1/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Given invalid value should return bad request")
    public void givenInvalidValueShouldReturnBadRequest() throws Exception {
        TransferRequest request = new TransferRequest(
                BigDecimal.valueOf(-1),
                1,
                2
        );

        mockMvc.perform(post("/api/v1/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isBadRequest());
    }


}