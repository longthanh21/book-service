package com.example.bookservice.messaging.event;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCopyStatusEvent {
    private List<Long> bookCopyIds;
    private Integer status; // BORROWED, AVAILABLE, LOST, DAMAGED
}
