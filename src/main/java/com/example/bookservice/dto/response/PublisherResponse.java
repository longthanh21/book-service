package com.example.bookservice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherResponse {
    private Long id;
    private String name;
    private String address;
    private String website;
}

