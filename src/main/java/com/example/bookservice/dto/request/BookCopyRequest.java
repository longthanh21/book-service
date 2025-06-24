package com.example.bookservice.dto.request;

import lombok.Data;

@Data
public class BookCopyRequest {
    private String barcode;
    private String status;
    private Long bookId;
    private Long locationId;
}

