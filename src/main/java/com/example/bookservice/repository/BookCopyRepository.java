package com.example.bookservice.repository;

import com.example.bookservice.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    int countByBookId(Long bookId);
}
