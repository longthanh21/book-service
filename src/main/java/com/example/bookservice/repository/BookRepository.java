package com.example.bookservice.repository;

import com.example.bookservice.dto.response.BookResponse;
import com.example.bookservice.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("""
    SELECT b FROM Book b
    WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(:title))
      AND (:isbn IS NULL OR b.isbn LIKE :isbn)
      AND (:author IS NULL OR LOWER(b.author.name) LIKE LOWER(:author))
      AND (:category IS NULL OR LOWER(b.category.name) LIKE LOWER(:category))
""")
    Page<Book> searchBooks(String title, String author, String isbn, String category, Pageable pageable);

}
