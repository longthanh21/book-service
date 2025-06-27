package com.example.bookservice.service;

import com.example.bookservice.dto.request.LocationRequest;
import com.example.bookservice.dto.response.LocationResponse;

import java.util.List;

public interface LocationService {

    LocationResponse create(LocationRequest request);

    List<LocationResponse> getAll();

    LocationResponse update(Long id, LocationRequest request);

    void delete(Long id);
}
