package com.example.bookservice.service;

import com.example.bookservice.dto.request.AuthorRequest;
import com.example.bookservice.dto.response.AuthorResponse;
import com.example.bookservice.model.Author;
import com.example.bookservice.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorResponse createAuthor(AuthorRequest request) {
        Author author = Author.builder()
                .name(request.getName())
                .bio(request.getBio())
                .build();
        author = authorRepository.save(author);
        return mapToResponse(author);
    }

    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AuthorResponse updateAuthor(Long id, AuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setName(request.getName());
        author.setBio(request.getBio());
        return mapToResponse(authorRepository.save(author));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    private AuthorResponse mapToResponse(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .bio(author.getBio())
                .build();
    }
}
