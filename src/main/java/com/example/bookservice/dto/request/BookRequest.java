package com.example.bookservice.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequest {
    private String title;
    private String isbn;
    private String description;
    private LocalDate publishedDate;
    private int pageCount;
    private String language;
    private String coverImageUrl;
}
