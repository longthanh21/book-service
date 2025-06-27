package com.example.bookservice.dto.request;

import lombok.Data;

@Data
public class MultipleBookCopiesRequest {
    private Long bookId;
    private Long locationId;
    private int quantity;
    private String status;
}
