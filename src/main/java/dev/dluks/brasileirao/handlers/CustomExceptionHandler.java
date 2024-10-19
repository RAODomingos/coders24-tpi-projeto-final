package dev.dluks.brasileirao.handlers;

import dev.dluks.brasileirao.dtos.response.CustomErrorResponse;
import dev.dluks.brasileirao.exceptions.InvalidCardExpception;
import dev.dluks.brasileirao.exceptions.InvalidGoalTypeException;
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

        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse error = new CustomErrorResponse(
                Instant.now(),
                e.getMessage(),
                request.getRequestURI(),
                status.value()
        );
        return ResponseEntity.status(status).body(error);

    }

   @ExceptionHandler(InvalidGoalTypeException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidGoalTypeException(
            InvalidGoalTypeException e,
            HttpServletRequest request) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse error = new CustomErrorResponse(
                Instant.now(),
                e.getMessage(),
                request.getRequestURI(),
                status.value()
        );
        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(InvalidCardExpception.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidCardExpception(
            InvalidCardExpception e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse error = new CustomErrorResponse(
                Instant.now(),
                e.getMessage(),
                request.getRequestURI(),
                status.value()
        );
        return ResponseEntity.status(status).body(error);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(
            Exception e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        CustomErrorResponse error = new CustomErrorResponse(
                Instant.now(),
                e.getMessage(),
                request.getRequestURI(),
                status.value()
        );
        return ResponseEntity.status(status).body(error);

    }
}
