package com.enmanuel.inditex.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceDTO {

    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priceList;
    private Long productId;
    private double price;
    private String currency;

}
