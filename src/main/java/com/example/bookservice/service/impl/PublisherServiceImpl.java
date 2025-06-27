package com.example.bookservice.service.impl;


import com.example.bookservice.dto.request.PublisherRequest;
import com.example.bookservice.dto.response.PublisherResponse;
import com.example.bookservice.model.Publisher;
import com.example.bookservice.repository.PublisherRepository;
import com.example.bookservice.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherResponse create(PublisherRequest request) {
        Publisher publisher = Publisher.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
        return mapToResponse(publisherRepository.save(publisher));
    }

    public List<PublisherResponse> getAll() {
        return publisherRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public PublisherResponse update(Long id, PublisherRequest request) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        publisher.setName(request.getName());
        publisher.setAddress(request.getAddress());

        return mapToResponse(publisherRepository.save(publisher));
    }

    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }

    private PublisherResponse mapToResponse(Publisher publisher) {
        return PublisherResponse.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .address(publisher.getAddress())
                .build();
    }
}
