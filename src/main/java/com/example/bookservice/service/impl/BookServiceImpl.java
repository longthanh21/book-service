package com.example.bookservice.service.impl;

import com.example.bookservice.dto.request.BookRequest;
import com.example.bookservice.dto.response.BookResponse;
import com.example.bookservice.model.Author;
import com.example.bookservice.model.Book;
import com.example.bookservice.model.Category;
import com.example.bookservice.model.Publisher;
import com.example.bookservice.repository.AuthorRepository;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.repository.CategoryRepository;
import com.example.bookservice.repository.PublisherRepository;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

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
                .authorId(book.getAuthor().getId()).authorName(book.getAuthor().getName())
                .categoryId(book.getCategory().getId()).categoryName(book.getCategory().getName())
                .publisherId(book.getPublisher().getId()).publisherName(book.getPublisher().getName())
                .status(book.getStatus())
                .build();
    }

    @Override
    public BookResponse createBook(BookRequest request) {

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author ID is not existed."));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category ID is not existed."));
        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new RuntimeException("Publisher ID is not existed."));

        Book book = Book.builder()
                .title(request.getTitle())
                .isbn(request.getIsbn())
                .description(request.getDescription())
                .publishedDate(request.getPublishedDate())
                .pageCount(request.getPageCount())
                .language(request.getLanguage())
                .coverImageUrl(request.getCoverImageUrl())
                .author(author)
                .category(category)
                .publisher(publisher)
                .status(request.getStatus())
                .build();
        return toDto(bookRepository.save(book));
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest request) {

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author ID is not existed."));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category ID is not existed."));
        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new RuntimeException("Publisher ID is not existed."));

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setDescription(request.getDescription());
        book.setPublishedDate(request.getPublishedDate());
        book.setPageCount(request.getPageCount());
        book.setLanguage(request.getLanguage());
        book.setCoverImageUrl(request.getCoverImageUrl());
        book.setAuthor(author);
        book.setCategory(category);
        book.setPublisher(publisher);
        book.setStatus(request.getStatus());

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
    public List<BookResponse> searchBooks(String title, String author, String isbn, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Book> bookPage = bookRepository.searchBooks(
                title != null ? "%" + title + "%" : null,
                author != null ? "%" + author + "%" : null,
                isbn != null ? "%" + isbn + "%" : null,
                category != null ? "%" + category + "%" : null,
                pageable
        );

        return bookPage.getContent().stream().map(this::toDto).toList();
    }

}
