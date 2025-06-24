package com.example.bookservice.service.impl;

import com.example.bookservice.dto.request.BookRequest;
import com.example.bookservice.dto.response.BookResponse;
import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private BookResponse toDto(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .description(book.getDescription())
                .publishedDate(book.getPublishedDate())
                .pageCount(book.getPageCount())
                .language(book.getLanguage())
                .coverImageUrl(book.getCoverImageUrl())
                .build();
    }

    @Override
    public BookResponse createBook(BookRequest request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .isbn(request.getIsbn())
                .description(request.getDescription())
                .publishedDate(request.getPublishedDate())
                .pageCount(request.getPageCount())
                .language(request.getLanguage())
                .coverImageUrl(request.getCoverImageUrl())
                .build();
        return toDto(bookRepository.save(book));
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setDescription(request.getDescription());
        book.setPublishedDate(request.getPublishedDate());
        book.setPageCount(request.getPageCount());
        book.setLanguage(request.getLanguage());
        book.setCoverImageUrl(request.getCoverImageUrl());

        return toDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponse getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> searchBooks(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
