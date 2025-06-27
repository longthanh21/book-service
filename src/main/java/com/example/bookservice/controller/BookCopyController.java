package com.example.bookservice.controller;

import com.example.bookservice.dto.request.BookCopyRequest;
import com.example.bookservice.dto.request.MultipleBookCopiesRequest;
import com.example.bookservice.dto.response.BookCopyResponse;
import com.example.bookservice.service.BookCopyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-copies")
@RequiredArgsConstructor
public class BookCopyController {

    private final BookCopyService bookCopyService;

    @PostMapping
    public ResponseEntity<BookCopyResponse> create(@RequestBody BookCopyRequest request) {
        return ResponseEntity.ok(bookCopyService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<BookCopyResponse>> getAll() {
        return ResponseEntity.ok(bookCopyService.getAll());
    }

    @PostMapping("/batch")
    public ResponseEntity<List<BookCopyResponse>> createMultiple(@RequestBody MultipleBookCopiesRequest request) {
        return ResponseEntity.ok(bookCopyService.createMultipleCopies(request));
    }

}
