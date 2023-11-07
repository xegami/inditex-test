package com.enmanuel.inditex;

import com.enmanuel.inditex.controller.PriceController;
import com.enmanuel.inditex.dto.PriceDTO;
import com.enmanuel.inditex.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceControllerAcceptanceTests {

    private final PriceService priceService = mock(PriceService.class);
    private final PriceController priceController = new PriceController(priceService);
    private final WebTestClient webTestClient = WebTestClient.bindToController(priceController).build();

    private PriceDTO getExpectedPriceDTO() {
        PriceDTO expectedPriceDTO = new PriceDTO();
        expectedPriceDTO.setBrandId(1L);
        expectedPriceDTO.setProductId(35455L);
        expectedPriceDTO.setCurrency("EUR");
        expectedPriceDTO.setPriceList(1);
        expectedPriceDTO.setPrice(50.0);
        expectedPriceDTO.setStartDate(LocalDateTime.parse("2023-11-07T20:00:00"));
        expectedPriceDTO.setEndDate(LocalDateTime.parse("2023-11-27T20:00:00"));
        return expectedPriceDTO;
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2023-11-14T10:00:00",
            "2023-11-14T16:00:00",
            "2023-11-14T21:00:00",
            "2023-11-15T10:00:00",
            "2023-11-16T21:00:00"})
    public void whenCorrectRequest_returns200(String input) {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.parse(input);

        PriceDTO expectedPriceDTO = getExpectedPriceDTO();

        when(priceService.findPrice(brandId, productId, date)).thenReturn(Mono.just(expectedPriceDTO));

        webTestClient.get()
                .uri("/prices?brandId={brandId}&productId={productId}&date={date}", brandId, productId, date)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceDTO.class)
                .isEqualTo(expectedPriceDTO);
    }

    @Test
    public void whenWrongRequest_returns404() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.now();

        when(priceService.findPrice(brandId, productId, date)).thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/prices?brandId={brandId}&productId={productId}&date={date}", brandId, productId, date)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }
}
