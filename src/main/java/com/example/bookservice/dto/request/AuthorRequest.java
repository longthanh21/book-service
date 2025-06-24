package com.example.bookservice.dto.request;

import lombok.Data;

@Data
public class AuthorRequest {
    private String name;
    private String bio;
}
