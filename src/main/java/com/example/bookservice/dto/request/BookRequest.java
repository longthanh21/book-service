package com.example.bookservice.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    private String description;

    @NotNull
    private LocalDate publishedDate;

    @Min(1)
    private int pageCount;

    private String language;

    private String coverImageUrl;

    @NotNull
    private Long authorId;

    @NotNull
    private Long publisherId;

    @NotNull
    private Long categoryId;

    @Column(nullable = false)
    private int status;
}
