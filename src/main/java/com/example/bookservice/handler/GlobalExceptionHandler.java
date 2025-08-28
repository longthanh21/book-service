package com.example.bookservice.handler;

import com.example.bookservice.dto.response.ErrorResponse;
import com.example.bookservice.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(UserServiceException.class)
//    public ResponseEntity<ErrorResponse> handleUserServiceException(UserServiceException ex, HttpServletRequest request) {
//        return ResponseEntity
//                .status(ex.getCode())
//                .body(new ErrorResponse(
//                        ex.getCode(),
//                        ex.getMessage(),
//                        "uri=" + request.getRequestURI()
//                ));
//    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        request.getDescription(false)
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        request.getDescription(false)
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal server error",
                        request.getDescription(false)
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }



}
