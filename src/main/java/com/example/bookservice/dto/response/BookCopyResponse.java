package com.example.bookservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookCopyResponse {
    private Long id;
    private String barcode;
    private String status;
    private Long bookId;
    private Long locationId;
}
