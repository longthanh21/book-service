package com.example.bookservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCopyResponse {
    private Long id;
    private String barcode;
    private String status;
    private Long bookId;
    private Long locationId;
}
