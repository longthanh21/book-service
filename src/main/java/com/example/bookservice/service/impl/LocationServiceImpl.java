package com.example.bookservice.service.impl;

import com.example.bookservice.dto.request.LocationRequest;
import com.example.bookservice.dto.response.LocationResponse;
import com.example.bookservice.model.Location;
import com.example.bookservice.repository.LocationRepository;
import com.example.bookservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public LocationResponse create(LocationRequest request) {
        Location location = Location.builder()
                .shelf(request.getShelf())
                .room(request.getRoom())
                .floor(request.getFloor())
                .build();
        return mapToResponse(locationRepository.save(location));
    }

    @Override
    public List<LocationResponse> getAll() {
        return locationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LocationResponse update(Long id, LocationRequest request) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        location.setShelf(request.getShelf());
        location.setRoom(request.getRoom());
        location.setFloor(request.getFloor());

        return mapToResponse(locationRepository.save(location));
    }

    @Override
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }

    private LocationResponse mapToResponse(Location location) {
        return LocationResponse.builder()
                .id(location.getId())
                .shelf(location.getShelf())
                .room(location.getRoom())
                .floor(location.getFloor())
                .build();
    }
}
