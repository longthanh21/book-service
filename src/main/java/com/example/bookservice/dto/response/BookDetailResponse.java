package com.example.bookservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BookDetailResponse {
    private Long id;
    private String language;
    private BigDecimal price;
    private boolean availableInMarket;
    private Integer pageCount;
}
