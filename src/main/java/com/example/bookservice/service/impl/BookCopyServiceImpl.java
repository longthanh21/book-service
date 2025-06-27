package com.example.bookservice.service.impl;

import com.example.bookservice.dto.request.BookCopyRequest;
import com.example.bookservice.dto.request.MultipleBookCopiesRequest;
import com.example.bookservice.dto.response.BookCopyResponse;
import com.example.bookservice.model.Book;
import com.example.bookservice.model.BookCopy;
import com.example.bookservice.model.Location;
import com.example.bookservice.repository.BookCopyRepository;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.repository.LocationRepository;
import com.example.bookservice.service.BookCopyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookCopyServiceImpl implements BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final LocationRepository locationRepository;

    @Override
    public BookCopyResponse create(BookCopyRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        BookCopy bookCopy = BookCopy.builder()
                .barcode(request.getBarcode())
                .status(request.getStatus())
                .book(book)
                .location(location)
                .build();

        BookCopy saved = bookCopyRepository.save(bookCopy);

        BookCopyResponse response = new BookCopyResponse();
        response.setId(saved.getId());
        response.setBarcode(saved.getBarcode());
        response.setStatus(saved.getStatus());
        response.setBookId(saved.getBook().getId());
        response.setLocationId(saved.getLocation().getId());

        return response;
    }

    @Override
    public List<BookCopyResponse> getAll() {
        return bookCopyRepository.findAll().stream().map(copy -> {
            BookCopyResponse response = new BookCopyResponse();
            response.setId(copy.getId());
            response.setBarcode(copy.getBarcode());
            response.setStatus(copy.getStatus());
            response.setBookId(copy.getBook().getId());
            response.setLocationId(copy.getLocation().getId());
            return response;
        }).collect(Collectors.toList());
    }

    @Override
    public List<BookCopyResponse> createMultipleCopies(MultipleBookCopiesRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        List<BookCopy> copies = new ArrayList<>();

        int existingBookCopies = bookCopyRepository.countByBookId(book.getId());

        for (int i = 1; i <= request.getQuantity(); i++) {
            int copyNumber = existingBookCopies + i;
            String barcode = "BOOK" + book.getId() + "-COPY" + copyNumber;

            BookCopy copy = BookCopy.builder()
                    .barcode(barcode)
                    .status(request.getStatus())
                    .book(book)
                    .location(location)
                    .build();

            copies.add(copy);
        }

        List<BookCopy> saved = bookCopyRepository.saveAll(copies);

        return saved.stream().map(copy -> {
            BookCopyResponse response = new BookCopyResponse();
            response.setId(copy.getId());
            response.setBarcode(copy.getBarcode());
            response.setStatus(copy.getStatus());
            response.setBookId(copy.getBook().getId());
            response.setLocationId(copy.getLocation().getId());
            return response;
        }).collect(Collectors.toList());
    }

}
