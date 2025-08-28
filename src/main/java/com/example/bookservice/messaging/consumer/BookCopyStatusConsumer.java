package com.example.bookservice.messaging.consumer;

import com.example.bookservice.messaging.event.BookCopyStatusEvent;
import com.example.bookservice.repository.BookCopyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookCopyStatusConsumer {

    private final BookCopyRepository bookCopyRepository;

    @Transactional
    @KafkaListener(topics = "book-copy-status", groupId = "book-service-group")
    public void handleBookCopyStatus(BookCopyStatusEvent event) {
        log.info("Nhận Kafka event cập nhật trạng thái bản sao sách: {}", event);

        int updated = bookCopyRepository.updateStatusByIds(event.getStatus(), event.getBookCopyIds());
        log.info("Đã cập nhật {} bản sao sách sang trạng thái {}", updated, event.getStatus());
    }
}
