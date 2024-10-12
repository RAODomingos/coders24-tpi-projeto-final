package dev.dluks.brasileirao.handlers;

import dev.dluks.brasileirao.dtos.CustomErrorResponse;
import dev.dluks.brasileirao.exceptions.InvalidYearException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidYearException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidYearException(
            InvalidYearException e,
            HttpServletRequest request) {

        CustomErrorResponse error = new CustomErrorResponse(
                Instant.now(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
