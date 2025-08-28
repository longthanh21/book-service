package com.example.bookservice.controller;

import com.example.bookservice.dto.request.BookCopyRequest;
import com.example.bookservice.dto.request.MultipleBookCopiesRequest;
import com.example.bookservice.dto.response.BookCopyResponse;
import com.example.bookservice.repository.BookCopyRepository;
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
    private final BookCopyRepository bookCopyRepository;

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

    @GetMapping("/{id}")
    public ResponseEntity<BookCopyResponse> getBookCopyById(@PathVariable Long id) {
        BookCopyResponse response = bookCopyService.getBookCopyById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id,
            @RequestParam("value") Integer newStatus
    ) {
        bookCopyService.updateStatus(id, newStatus);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info")
    public List<BookCopyResponse> getInfo(@RequestParam List<Long> ids) {
        return bookCopyRepository.findAllById(ids).stream()
                .map(copy -> BookCopyResponse.builder()
                        .id(copy.getId())
                        .status(copy.getStatus())
                        .barcode(copy.getBarcode())
                        .bookId(copy.getBook().getId())
                        .bookTitle(copy.getBook().getTitle())
                        .locationId(copy.getLocation().getId())
                        .locationName(
                                copy.getLocation().getShelf()
                                        + ", " + copy.getLocation().getRoom()
                                        + ", " + copy.getLocation().getFloor()
                        )
                        .build())
                .toList();
    }

}
