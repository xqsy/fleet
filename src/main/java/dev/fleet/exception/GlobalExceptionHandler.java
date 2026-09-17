package dev.fleet.exception;

import dev.fleet.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.fleet.workload.DriverWorkloadLimitException;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            DriverNotFoundException.class,
            VehicleNotFoundException.class,
            TripNotFoundException.class,
            TransportRequestNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFound(RuntimeException exception, HttpServletRequest request) {
        return new ErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                Instant.now(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler({
            InvalidOperationException.class,
            VehicleNotSuitableException.class,
            DriverWorkloadLimitException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleInvalidOperation(RuntimeException exception, HttpServletRequest request) {
        return new ErrorResponse(
                exception.getMessage(),
                HttpStatus.CONFLICT.value(),
                Instant.now(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ErrorResponse(
                message,
                HttpStatus.BAD_REQUEST.value(),
                Instant.now(),
                request.getRequestURI()
        );
    }
}
