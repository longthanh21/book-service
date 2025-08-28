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
    private Integer status;
    private Long bookId;
    private String bookTitle;
    private Long locationId;
    private String locationName;
}
