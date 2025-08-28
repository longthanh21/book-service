package com.example.bookservice.service;

import com.example.bookservice.dto.request.BookCopyRequest;
import com.example.bookservice.dto.request.MultipleBookCopiesRequest;
import com.example.bookservice.dto.response.BookCopyResponse;

import java.util.List;

public interface BookCopyService {

    BookCopyResponse create(BookCopyRequest request);

    List<BookCopyResponse> getAll();

    List<BookCopyResponse> createMultipleCopies(MultipleBookCopiesRequest request);

    BookCopyResponse getBookCopyById(Long id);

    void updateStatus(Long id, Integer newStatus);
}
