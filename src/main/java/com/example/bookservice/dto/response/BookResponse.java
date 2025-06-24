package com.example.bookservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String isbn;
    private String description;
    private LocalDate publishedDate;
    private int pageCount;
    private String language;
    private String coverImageUrl;
}
