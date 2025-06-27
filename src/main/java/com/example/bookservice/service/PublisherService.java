package com.example.bookservice.service;

import com.example.bookservice.dto.request.PublisherRequest;
import com.example.bookservice.dto.response.PublisherResponse;
import com.example.bookservice.model.Publisher;

import java.util.List;

public interface PublisherService {

     PublisherResponse create(PublisherRequest request);

     List<PublisherResponse> getAll();

     PublisherResponse update(Long id, PublisherRequest request);

     void delete(Long id);
}

