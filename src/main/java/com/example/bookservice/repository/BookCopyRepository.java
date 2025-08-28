package com.example.bookservice.repository;

import com.example.bookservice.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    int countByBookId(Long bookId);

    @Modifying
    @Query("UPDATE BookCopy bc SET bc.status = :status WHERE bc.id IN :ids")
    int updateStatusByIds(Integer status, List<Long> ids);
}
